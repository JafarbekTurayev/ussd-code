package pdp.uz.lesson6.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pdp.uz.lesson6.component.DetailExporter;
import pdp.uz.lesson6.entity.Client;
import pdp.uz.lesson6.entity.Details;
import pdp.uz.lesson6.entity.SimCard;
import pdp.uz.lesson6.entity.enums.ActionType;
import pdp.uz.lesson6.payload.ApiResponse;
import pdp.uz.lesson6.payload.DetailDto;
import pdp.uz.lesson6.repository.DetailsRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("ransomware")
public class DetailsService {

    @Autowired
    DetailsRepository detailsRepository;

    @Autowired
    DetailExporter exporter;

    //ma'lumotlarni pdf qilib export qilish
    public ApiResponse exportToPdf(HttpServletResponse response, List<Details> detailsList, String code) throws IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentTime = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=detail_" + currentTime + ".pdf";//fayl nomini o'zgartirish kk

        response.setHeader(headerKey, headerValue);

        //ma'lumotlarni export qilish
        exporter.exportPDF(response, detailsList, code);
        return new ApiResponse("Successfully exported", true);
    }

    //ma'lumotlarni excel qilib export qilish
    public ApiResponse exportToExcel(HttpServletResponse response, List<Details> detailsList) throws IOException {
        response.setContentType("application/octet-stream");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentTime = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=user_"+currentTime+".xlsx";

        response.setHeader(headerKey,headerValue);
        exporter.exportExcel(response, detailsList);
        return new ApiResponse("Successfully exported", true);
    }

    //Simkarta bo'yicha ma'lumotlarni pdfga export qilish
    public ApiResponse getAllBySimCard(HttpServletResponse response, String simcardId, boolean stat) throws IOException {
        Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<SimCard> simCardList = client.getSimCardList();
        for (SimCard card : simCardList) {
            if (card.getId().toString().equals(simcardId)){
                List<Details> detailsList = detailsRepository.findAllBySimCard(card);
                return stat?exportToExcel(response, detailsList):exportToPdf(response, detailsList, card.getCode());
            }
        }
        return new ApiResponse("Error!", false);
    }

    //Simkarta va action type bo'yicha ma'lumotlarni qaytarish
    public ApiResponse getAllBySimCardAndActionType(HttpServletResponse response, String simcardId, String action, boolean stat) throws IOException {
        Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<SimCard> simCardList = client.getSimCardList();
        for (SimCard card : simCardList) {
            if (card.getId().toString().equals(simcardId)){
                ActionType actionType = null;
                for (ActionType value : ActionType.values()) {
                    if (value.toString().equalsIgnoreCase(action)){
                        actionType = value;
                        break;
                    }
                }
                if (actionType == null)
                    return new ApiResponse("Error action type!", false);

                List<Details> detailsList = detailsRepository.findAllByActionTypeAndSimCard(actionType,card);
                return stat?exportToExcel(response, detailsList):exportToPdf(response, detailsList, card.getCode());
            }
        }
        return new ApiResponse("Error!", false);
    }

    //details qo'shish
    public ApiResponse add(DetailDto detailDto){
        Client principal = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        for (SimCard simCard : principal.getSimCardList()) {
            if (simCard.getId().toString().equals(detailDto.getSimCard().getId().toString())){
                Details details = new Details();
                details.setAmount(detailDto.getAmount());
                details.setActionType(detailDto.getActionType());
                details.setSimCard(detailDto.getSimCard());
                detailsRepository.save(details);
                return new ApiResponse("Details saqlandi!", true);
            }
        }
        return new ApiResponse("Error!", false);
    }
}
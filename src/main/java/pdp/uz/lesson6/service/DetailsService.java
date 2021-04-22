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

    //Simkarta bo'yicha ma'lumotlarni pdfga export qilish //stat=0 -> pdf; stat=1 -> excel; stat=2 -> table
    public ApiResponse getAllBySimCard(HttpServletResponse response, Integer stat) throws IOException {
        SimCard card = (SimCard) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Details> detailsList = detailsRepository.findAllBySimCard(card);
        if (stat==0)
            return exportToPdf(response, detailsList, card.getCode());
        if (stat==1)
            return exportToExcel(response, detailsList);
        if (stat==2)
            return new ApiResponse("List for details",true,detailsList);

        return new ApiResponse("Error!",false);
//        return stat==0?exportToPdf(response, detailsList, card.getCode()):stat==1?exportToExcel(response, detailsList):stat==2?new ApiResponse("List for details",true,detailsList):new ApiResponse("Error!", false);
    }

    //Simkarta va action type bo'yicha ma'lumotlarni qaytarish
    public ApiResponse getAllBySimCardAndActionType(HttpServletResponse response, String action, Integer stat) throws IOException {
        SimCard card = (SimCard) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

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
        if (stat==0)
            return exportToPdf(response, detailsList, card.getCode());
        if (stat==1)
            return exportToExcel(response, detailsList);
        if (stat==2)
            return new ApiResponse("List for details",true,detailsList);

        return new ApiResponse("Error!",false);
//        return stat==0?exportToPdf(response, detailsList, card.getCode()):stat==1?exportToExcel(response, detailsList):stat==2?new ApiResponse("List for details",true,detailsList):new ApiResponse("Error!", false);
    }

    //details qo'shish
    public ApiResponse add(DetailDto detailDto){
        SimCard principal = (SimCard) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (principal.getId().toString().equals(detailDto.getSimCard().getId().toString())){
                Details details = new Details();
                details.setAmount(detailDto.getAmount());
                details.setActionType(detailDto.getActionType());
                details.setSimCard(detailDto.getSimCard());
                detailsRepository.save(details);
                return new ApiResponse("Details saqlandi!", true);
            }

        return new ApiResponse("Error!", false);
    }
}
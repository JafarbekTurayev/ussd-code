package pdp.uz.lesson6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pdp.uz.lesson6.entity.Payment;
import pdp.uz.lesson6.entity.Role;
import pdp.uz.lesson6.entity.SimCard;
import pdp.uz.lesson6.entity.Staff;
import pdp.uz.lesson6.entity.enums.PayType;
import pdp.uz.lesson6.entity.enums.RoleName;
import pdp.uz.lesson6.payload.ApiResponse;
import pdp.uz.lesson6.payload.PaymentDto;
import pdp.uz.lesson6.repository.PaymentRepository;
import pdp.uz.lesson6.repository.RoleRepository;
import pdp.uz.lesson6.repository.SimCardRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PaymentService {
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    SimCardRepository simCardRepository;
    @Autowired
    RoleRepository roleRepository;

    public ApiResponse add(PaymentDto dto){
        Payment payment = new Payment();
        payment.setPayerId(dto.getPayerId());
        payment.setPayerName(dto.getPayerName());
        payment.setAmount(dto.getAmount());
        SimCard simCard = simCardRepository.findBySimCardNumber(dto.getSimCardNumber()).get();
        simCard.setBalance(simCard.getBalance()+ dto.getAmount());
        payment.setSimCard(simCard);
        if (dto.getPayType().equalsIgnoreCase("naqd")) payment.setPayType(PayType.NAQD);
        if (dto.getPayType().equalsIgnoreCase("humo")) payment.setPayType(PayType.HUMO);
        if (dto.getPayType().equalsIgnoreCase("click")) payment.setPayType(PayType.CLICK);
        if (dto.getPayType().equalsIgnoreCase("payme")) payment.setPayType(PayType.PAYME);
        paymentRepository.save(payment);
        return new ApiResponse("Payment amalga oshirildi", true);

    }
    //hodimlar tomonidan barcha tolovlar tarihini korish
    public ApiResponse getAll(){
        List<Payment> paymentList = paymentRepository.findAll();
        return new ApiResponse("Barcha tolovlar tarihi", true, paymentList);
    }

    //hodimlar tomonidan faqat bitta mijoz sim kartasining tolovlar tarihini korish
    public ApiResponse getOneClientsPaymentHistory(String simCardNumber){
        List<Payment> allBySimCardNumber = paymentRepository.findAllBySimCardNumber(simCardNumber);
        SimCard simCard = simCardRepository.findBySimCardNumber(simCardNumber).get();
        return new ApiResponse(simCard.getCode()+simCard.getNumber()+
                " raqamli abonentning tolovlar tarihi", true, allBySimCardNumber);
    }
    //mijoz tomonidan ozining barcha tolovlar tarihini korish
    public ApiResponse getPaymentHistoryByClient(){
      SimCard simCard = (SimCard) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Payment> allBySimCardNumber = paymentRepository.findAllBySimCardNumber(simCard.getSimCardNumber());
        return new ApiResponse(simCard.getCode()+simCard.getNumber()+" " +
                "raqimingizning tolovlar tarihi", true, allBySimCardNumber);
    }



}

package pdp.uz.lesson6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pdp.uz.lesson6.component.NumberGenerator;
import pdp.uz.lesson6.component.SerialNumberGenerator;
import pdp.uz.lesson6.entity.*;
import pdp.uz.lesson6.entity.enums.ClientType;
import pdp.uz.lesson6.entity.enums.RoleName;
import pdp.uz.lesson6.payload.*;
import pdp.uz.lesson6.repository.ClientRepository;
import pdp.uz.lesson6.repository.RoleRepository;
import pdp.uz.lesson6.repository.SimCardRepository;
import pdp.uz.lesson6.repository.TariffRepository;
import sun.util.calendar.LocalGregorianCalendar;

import java.sql.Timestamp;
import java.util.*;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    SimCardRepository simCardRepository;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    NumberGenerator numberGenerator;
    @Autowired
    TariffRepository tariffRepository;





    public ApiResponse requestCredit(DebtDto debtDto) {
        SimCard simCard = (SimCard) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (simCard.isCredit() || simCard.getBalance() > 5000)
            return new ApiResponse("Sizga qarz berilmaydi", false);
        if (debtDto.getAmount() > 25000) return new ApiResponse("Siz faqat 25000 gacha qarz ola olasiz", false);

        simCard.setCredit(true);
        simCard.setBalance(simCard.getBalance() + debtDto.getAmount());


        DebtSimCard debtSimCard = new DebtSimCard();
        debtSimCard.setSimCard(simCard);
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(System.currentTimeMillis());
        calendar.add(Calendar.DAY_OF_YEAR, 3);
        Date expireTime = calendar.getTime();
        debtSimCard.setExpireDate(new Timestamp(expireTime.getTime()));
        debtSimCard.setAmount(debtDto.getAmount());

        simCard.setDebtSimCards(Collections.singletonList(debtSimCard));

        simCardRepository.save(simCard);
        return new ApiResponse("Sizga qarz berildi", true);


    }

    public ApiResponse callSmb(CallDto callDto) {

        SimCard simCard = (SimCard) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Tariff tariff = simCard.getTariff();
    return null;
    }

}

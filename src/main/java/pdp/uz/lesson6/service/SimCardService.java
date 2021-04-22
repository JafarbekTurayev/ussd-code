package pdp.uz.lesson6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.uz.lesson6.component.NumberGenerator;
import pdp.uz.lesson6.entity.SimCard;
import pdp.uz.lesson6.payload.ApiResponse;
import pdp.uz.lesson6.repository.ClientRepository;
import pdp.uz.lesson6.repository.RoleRepository;
import pdp.uz.lesson6.repository.SimCardRepository;
import pdp.uz.lesson6.repository.TariffRepository;

import java.util.Optional;

@Service
public class SimCardService {
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






    public ApiResponse unableSimCard(String code, String number) {

        Optional<SimCard> optionalSimCard = simCardRepository.findByCodeAndNumber(code, number);
        if (!optionalSimCard.isPresent()) return new ApiResponse("Sim karta topilmadi", false);
        SimCard simCard = optionalSimCard.get();
        simCard.setActive(false);
        simCardRepository.save(simCard);
        return new ApiResponse("Sim karta deactivatsiya qilindi", true);
    }
}

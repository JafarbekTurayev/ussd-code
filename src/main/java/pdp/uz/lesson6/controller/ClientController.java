package pdp.uz.lesson6.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pdp.uz.lesson6.entity.SimCard;
import pdp.uz.lesson6.payload.ApiResponse;

@RestController
@RequestMapping("/api/client")
public class ClientController {



    // USSD cod bilan/appdan  balansni tekshirish
    @GetMapping("/checkBalance")
    public ApiResponse checkBalance(@RequestParam String phoneNumber){
       SimCard simCard = (SimCard) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       return new ApiResponse("Balansingizdagi mablag =>"+simCard.getBalance(), true);
    }




}

package pdp.uz.lesson6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pdp.uz.lesson6.entity.SimCard;
import pdp.uz.lesson6.payload.*;
import pdp.uz.lesson6.service.ClientService;

@RestController
@RequestMapping("/api/client")
public class ClientController {


    @Autowired
    ClientService clientService;

    @PreAuthorize(value = "hasAnyRole(ROLE_STAFF,ROLE_CLIENT)")
    @PostMapping("/buySimCard")
    public ResponseEntity<?> buySimCard(@RequestBody ClientDto clientDto) {
        ApiResponse apiResponse = clientService.buySimCard(clientDto);
        if (apiResponse.isSuccess()) return ResponseEntity.ok(apiResponse);
        return ResponseEntity.status(409).body(apiResponse);
    }
    @PreAuthorize(value = "hasRole(ROLE_CLIENT)")
    @PostMapping("/requestDebt")
    public ResponseEntity<?> requestDebt(@RequestBody DebtDto debtDto) {
        ApiResponse apiResponse = clientService.requestCredit(debtDto);
        if (apiResponse.isSuccess()) return ResponseEntity.ok(apiResponse);
        return ResponseEntity.status(409).body(apiResponse);
    }
    @PreAuthorize(value = "hasRole(ROLE_CLIENT)")
    @PostMapping("/call")
    public ResponseEntity<?> call(@RequestBody CallDto callDto) {
        ApiResponse apiResponse = clientService.callSmb(callDto);
        if (apiResponse.isSuccess()) return ResponseEntity.ok(apiResponse);
        return ResponseEntity.status(409).body(apiResponse);
    }
    @PreAuthorize(value = "hasRole(ROLE_CLIENT)")
    @PostMapping("/sendSms")
    public ResponseEntity<?> sendSms(@RequestBody SmsDto smsDto) {
        ApiResponse apiResponse = clientService.sendSms(smsDto);
        if (apiResponse.isSuccess()) return ResponseEntity.ok(apiResponse);
        return ResponseEntity.status(409).body(apiResponse);
    }


}

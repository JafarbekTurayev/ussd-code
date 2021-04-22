package pdp.uz.lesson6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.uz.lesson6.payload.ApiResponse;
import pdp.uz.lesson6.payload.PaymentDto;
import pdp.uz.lesson6.service.PaymentService;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth/payment")
public class PaymentController {
    @Autowired
    PaymentService service;

    @PostMapping
    public HttpEntity<?> addPayment(@RequestBody PaymentDto dto) {
        ApiResponse apiResponse = service.add(dto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @GetMapping("/{simCardNumber}")
    public ResponseEntity<?>getOneClientsPaymentHistory(String simCardNumber){
        return ResponseEntity.ok(service.getOneClientsPaymentHistory(simCardNumber));
    }

    @GetMapping("/byClient")
    public ResponseEntity<?>getPaymentHistoryByClient(){
        return ResponseEntity.ok(service.getPaymentHistoryByClient());
    }

    @GetMapping
    public ResponseEntity<?>getAllPaymentHistory(){
        return ResponseEntity.ok(service.getAll());
    }






}

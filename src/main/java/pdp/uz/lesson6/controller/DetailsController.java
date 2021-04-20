package pdp.uz.lesson6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.uz.lesson6.payload.ApiResponse;
import pdp.uz.lesson6.service.DetailsService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/details")
public class DetailsController {

    @Autowired
    DetailsService detailsService;

    @GetMapping("/pdf/{id}")
    public HttpEntity<?> getAllPdf(HttpServletResponse response, @PathVariable String id) throws IOException {
        ApiResponse apiResponse = detailsService.getAllBySimCard(response, id, false);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.OK:HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @GetMapping("/excel/{id}")
    public HttpEntity<?> getAllExcel(HttpServletResponse response, @PathVariable String id) throws IOException {
        ApiResponse apiResponse = detailsService.getAllBySimCard(response, id, true);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.OK:HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @GetMapping("/pdf/{id}")
    public HttpEntity<?> getAllPdfWithAction(HttpServletResponse response, @PathVariable String id, @RequestParam String action) throws IOException {
        ApiResponse apiResponse = detailsService.getAllBySimCardAndActionType(response, id, action,false);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.OK:HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @GetMapping("/excel/{id}")
    public HttpEntity<?> getAllExcelWithAction(HttpServletResponse response, @PathVariable String id, @RequestParam String action) throws IOException {
        ApiResponse apiResponse = detailsService.getAllBySimCardAndActionType(response, id,action,true);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.OK:HttpStatus.BAD_REQUEST).body(apiResponse);
    }
}

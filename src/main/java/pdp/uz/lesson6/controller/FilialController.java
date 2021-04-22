package pdp.uz.lesson6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pdp.uz.lesson6.entity.Filial;
import pdp.uz.lesson6.entity.Staff;
import pdp.uz.lesson6.payload.ApiResponse;
import pdp.uz.lesson6.payload.FilialDto;
import pdp.uz.lesson6.payload.StaffDto;
import pdp.uz.lesson6.service.FilialService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/filial")
public class FilialController {
    @Autowired
    FilialService filialService;

    // Filial qo'shish
    @PreAuthorize(value = "hasAnyRole('ROLE_MANAGER')")
    @PostMapping
    public HttpEntity<?> addFilial(FilialDto filialDto){
        ApiResponse apiResponse = filialService.addFilial(filialDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    // Filial tahrirlash
    @PreAuthorize(value = "hasAnyRole('ROLE_MANAGER')")
    @PutMapping("/{id}")
    public HttpEntity<?> editFilial(@PathVariable Integer id, FilialDto filialDto){
        ApiResponse apiResponse = filialService.editFilial(id, filialDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    // Id bo'yicha filial qaytaradi
    @PreAuthorize(value = "hasAnyRole('ROLE_DIRECTOR','ROLE_MANAGER')")
    @GetMapping("/{id}")
    public HttpEntity<?> getFilial(@PathVariable Integer id){
        ApiResponse filial = filialService.getFilial(id);
        return ResponseEntity.ok(filial);
    }

    // Barcha filial lar ro'yxati
    @PreAuthorize(value = "hasAnyRole('ROLE_DIRECTOR','ROLE_MANAGER')")
    @GetMapping
    public HttpEntity<?> getFilialList(){
        List<Filial> filialList = filialService.getFilialList();
        return ResponseEntity.ok(filialList);
    }


    // Id bo'yicha filial o'chiradi
    @PreAuthorize(value = "hasAnyRole('ROLE_MANAGER')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteFilial(@PathVariable Integer id){
        ApiResponse apiResponse = filialService.deleteFilial(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}

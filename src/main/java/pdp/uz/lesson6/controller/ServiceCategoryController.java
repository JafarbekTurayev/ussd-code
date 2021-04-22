package pdp.uz.lesson6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import pdp.uz.lesson6.entity.ServiceCategory;
import pdp.uz.lesson6.payload.ApiResponse;
import pdp.uz.lesson6.payload.CategoryServiceDto;
import pdp.uz.lesson6.service.CategoryServiceService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/serviceCategory")
public class ServiceCategoryController {
    @Autowired
    CategoryServiceService categoryServiceService;
    @GetMapping("/list")

    public HttpEntity<?> getList(){
        List<ServiceCategory> categoriesList = categoryServiceService.getCategoriesList();
        return ResponseEntity.status(201).body(categoriesList);
    }
    @GetMapping("/getById/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id){
        ApiResponse serviceCategoryById = categoryServiceService.getServiceCategoryById(id);
        return ResponseEntity.status(201).body(serviceCategoryById);
    }
    @PutMapping("/edit/{id}")
    @Secured({ "ROLE_MANAGER"})
    public HttpEntity<?> editCategory(@PathVariable Integer id, @RequestBody CategoryServiceDto categoryServiceDto){
        ApiResponse apiResponse = categoryServiceService.editCategory(categoryServiceDto, id);
        return ResponseEntity.status(201).body(apiResponse);
    }
    @PostMapping("/add")
    @Secured({ "ROLE_MANAGER"})
    public HttpEntity<?> addCategory(@RequestBody CategoryServiceDto categoryServiceDto){
        ApiResponse apiResponse = categoryServiceService.addCategory(categoryServiceDto);
        return ResponseEntity.status(201).body(apiResponse);
    }

}

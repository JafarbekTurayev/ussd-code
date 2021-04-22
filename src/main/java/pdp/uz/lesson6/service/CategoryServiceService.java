package pdp.uz.lesson6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.uz.lesson6.entity.ServiceCategory;
import pdp.uz.lesson6.payload.ApiResponse;
import pdp.uz.lesson6.payload.CategoryServiceDto;
import pdp.uz.lesson6.repository.ServiceCategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceService {
@Autowired
    ServiceCategoryRepository serviceCategoryRepository;
public List<ServiceCategory> getCategoriesList(){
    return serviceCategoryRepository.findAll();
}
public ApiResponse getServiceCategoryById(Integer id){
    Optional<ServiceCategory> optionalServiceCategory = serviceCategoryRepository.findById(id);
    if (!optionalServiceCategory.isPresent()){
        return new ApiResponse("category id not founded",false,null);
    }
    ServiceCategory category = optionalServiceCategory.get();
    return new ApiResponse("category founded",true,category);
}
public ApiResponse addCategory(CategoryServiceDto categoryServiceDto){
    boolean exists = serviceCategoryRepository.existsByName(categoryServiceDto.getName());
    if (exists)
        return new ApiResponse("Bunday Category mavjud",false,null);
    ServiceCategory serviceCategory = new ServiceCategory();
    serviceCategory.setName(categoryServiceDto.getName());
    serviceCategoryRepository.save(serviceCategory);
    return new ApiResponse("Category qo'shildi",false,serviceCategory);

}
public ApiResponse editCategory(CategoryServiceDto categoryServiceDto,Integer id){
    Optional<ServiceCategory> categoryOptional = serviceCategoryRepository.findById(id);
    if (!categoryOptional.isPresent())
        return new ApiResponse("category id not founded",false,null);
    ServiceCategory serviceCategory = categoryOptional.get();
    boolean exists = serviceCategoryRepository.existsByName(categoryServiceDto.getName());
    if (exists)
        return new ApiResponse("bunday category mavjud",false,null);
    serviceCategory.setName(categoryServiceDto.getName());
    serviceCategoryRepository.save(serviceCategory);
    return new ApiResponse("Category o'zgartirildi",true,serviceCategory);
}

}

package pdp.uz.lesson6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import pdp.uz.lesson6.entity.EntertainingService;
import pdp.uz.lesson6.entity.ServiceCategory;
import pdp.uz.lesson6.payload.ApiResponse;
import pdp.uz.lesson6.payload.ServiceDto;
import pdp.uz.lesson6.repository.ServiceCategoryRepository;
import pdp.uz.lesson6.repository.ServiceRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ServiceService {
    @Autowired
    ServiceRepository serviceRepository;
    @Autowired
    ServiceCategoryRepository serviceCategoryRepository;
    public List<EntertainingService> getServiceList(){
        return serviceRepository.findAll();
    }
    public ApiResponse getServiceById(UUID id){
        Optional<EntertainingService> serviceOptional = serviceRepository.findById(id);
        if (!serviceOptional.isPresent())
        return new ApiResponse("bunday id dagi xizmat topilmadi",false,null);
        EntertainingService entertainingService = serviceOptional.get();
        return new ApiResponse("So'rov muvaffaqiyatli yakunladni",false,entertainingService);
    }
    public ApiResponse addEntertainingService(ServiceDto serviceDto){
        boolean exists = serviceRepository.findByName(serviceDto.getName());
        if (exists)
            return new ApiResponse("bunday nomli xizmat mavjud",false,null);
        EntertainingService entertainingService = new EntertainingService();
    entertainingService.setName(serviceDto.getName());
    entertainingService.setExpiredDate(serviceDto.getExpiredDate());
    entertainingService.setPrice(serviceDto.getPrice());
        Optional<ServiceCategory> categoryOptional = serviceCategoryRepository.findById(serviceDto.getServiceCategoryID());
        if (!categoryOptional.isPresent()){
            ServiceCategory serviceCategory = new ServiceCategory();
            serviceCategory.setName(serviceDto.getCategoryName());
            serviceCategoryRepository.save(serviceCategory);
        }
        ServiceCategory serviceCategory = categoryOptional.get();
        entertainingService.setServiceCategory(serviceCategory);
        serviceRepository.save(entertainingService);
        return new ApiResponse("qo'shildi",true,entertainingService);
    }
//    @Secured({ "ROLE_VIEWER", "ROLE_EDITOR" })
    public ApiResponse editEntertainingService(UUID id,ServiceDto serviceDto){
        Optional<EntertainingService> serviceOptional = serviceRepository.findById(id);
        if (!serviceOptional.isPresent())
            return new ApiResponse("Bunday id dagi xizmat topilmadi",false,null);
        EntertainingService entertainingService = serviceOptional.get();
        boolean exist = serviceRepository.findByName(serviceDto.getName());
        if (exist)
            return new ApiResponse("bunday xizmat turi mavjud",false,null);
 entertainingService.setName(serviceDto.getName());
 entertainingService.setPrice(serviceDto.getPrice());
 entertainingService.setExpiredDate(serviceDto.getExpiredDate());
        Optional<ServiceCategory> categoryOptional = serviceCategoryRepository.findById(serviceDto.getServiceCategoryID());
        if (!categoryOptional.isPresent()){
           ServiceCategory serviceCategory = new ServiceCategory();
           serviceCategory.setName(serviceDto.getCategoryName());
           serviceCategoryRepository.save(serviceCategory);
        }
        ServiceCategory serviceCategory = categoryOptional.get();
        entertainingService.setServiceCategory(serviceCategory);
        serviceRepository.save(entertainingService);
        return new ApiResponse("Xizmat muvaffaqiyatli o'zgartirildi",false,entertainingService);
    }
//public ApiResponse addEntertainingServiceForClient
}

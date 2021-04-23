package pdp.uz.lesson6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pdp.uz.lesson6.entity.Role;
import pdp.uz.lesson6.entity.Staff;
import pdp.uz.lesson6.entity.UssdCode;
import pdp.uz.lesson6.entity.enums.RoleName;
import pdp.uz.lesson6.payload.ApiResponse;
import pdp.uz.lesson6.repository.RoleRepository;
import pdp.uz.lesson6.repository.UssdCodeRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UssdCodeService {
    @Autowired
    UssdCodeRepository ussdCodeRepository;
    @Autowired
    RoleRepository roleRepository;

    public ApiResponse add(UssdCode ussdCode) {

        Staff hodim = (Staff) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<Role> userRoles = hodim.getRoles();
        Role manager = roleRepository.findByRoleName(RoleName.ROLE_MANAGER);
        Role staff = roleRepository.findByRoleName(RoleName.ROLE_STAFF);
        if ((hodim.getRoles().contains(staff)) ||
                (hodim.getRoles().contains(manager) && hodim.getPosition().equalsIgnoreCase("USSD_Manager")))
            return new ApiResponse("Siz yangi USSD sorovlarini qosholmaysiz", false);
        UssdCode ussdCode1 = new UssdCode();
        ussdCode1.setCode(ussdCode.getCode());
        ussdCode1.setDescription(ussdCode.getDescription());
        ussdCodeRepository.save(ussdCode1);
        return new ApiResponse("Yangi USSD sorov qoshildi", true);

    }
    public ApiResponse delete(Integer id){
        Staff hodim = (Staff) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<Role> userRoles = hodim.getRoles();
        Role manager = roleRepository.findByRoleName(RoleName.ROLE_MANAGER);
        Role staff = roleRepository.findByRoleName(RoleName.ROLE_STAFF);
        if ((hodim.getRoles().contains(staff)) ||
                (hodim.getRoles().contains(manager) && hodim.getPosition().equalsIgnoreCase("USSD_Manager")))
            return new ApiResponse("Siz  USSD sorovlarini ochirolmaysiz", false);
        ussdCodeRepository.deleteById(id);
        return new ApiResponse("USSD kodi ochirildi", true);
    }

    public ApiResponse edit(Integer id, UssdCode ussdCode){
        Staff hodim = (Staff) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<Role> userRoles = hodim.getRoles();
        Role manager = roleRepository.findByRoleName(RoleName.ROLE_MANAGER);
        Role staff = roleRepository.findByRoleName(RoleName.ROLE_STAFF);
        if ((hodim.getRoles().contains(staff)) ||
                (hodim.getRoles().contains(manager) && hodim.getPosition().equalsIgnoreCase("USSD_Manager")))
            return new ApiResponse("Siz  USSD sorovlarini tahrir qilolmaysiz", false);
        Optional<UssdCode> optionalUssdCode = ussdCodeRepository.findById(id);
        if (!optionalUssdCode.isPresent()) return new ApiResponse("Bunday IDli USSD kod mavjud emas", false);
        UssdCode ussdCode1 = optionalUssdCode.get();
        ussdCode1.setCode(ussdCode.getCode());
        ussdCode1.setDescription(ussdCode.getDescription());
        ussdCodeRepository.save(ussdCode1);
        return new ApiResponse("USSD kodi tahrirlandi", true);
    }

    public ApiResponse getAll(){
        List<UssdCode> all = ussdCodeRepository.findAll();
        return new ApiResponse("Barcha USSD kodilarii", true, all);
    }

    public ApiResponse getOne(Integer id){
        Optional<UssdCode> optionalUssdCode = ussdCodeRepository.findById(id);
        return optionalUssdCode.map(ussdCode -> new ApiResponse("Siz qidirgan USSD kodi", true, ussdCode)).orElseGet(() -> new ApiResponse("Bunday IDli USSD kod mavjud emas", false));
    }



}

package pdp.uz.lesson6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.uz.lesson6.repository.FilialRepository;
import pdp.uz.lesson6.repository.RoleRepository;

@Service
public class FilialService {
    @Autowired
    FilialRepository filialRepository;
    @Autowired
    RoleRepository roleRepository;


//    public ApiResponse add(FilialDto dto) {
//
//
//        Staff hodim = (Staff) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Set<Role> userRoles = hodim.getRoles();
//        Role director = roleRepository.findByRoleName(RoleName.ROLE_DIRECTOR);
//        Role manager = roleRepository.findByRoleName(RoleName.ROLE_MANAGER);
//        Role staff = roleRepository.findByRoleName(RoleName.ROLE_STAFF);
//        Role client = roleRepository.findByRoleName(RoleName.ROLE_CLIENT);
//
//        if ((userRoles.contains(staff)) ||
//                ((userRoles.contains(manager) && (userRoles.contains(client) &&
//                        !hodim.getPosition().equalsIgnoreCase("Filials_manager")))))
//        return new ApiResponse("You can not add new filial", false);
//
//
//
//    }

}

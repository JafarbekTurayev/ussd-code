package pdp.uz.lesson6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pdp.uz.lesson6.entity.Filial;
import pdp.uz.lesson6.entity.Role;
import pdp.uz.lesson6.entity.Staff;
import pdp.uz.lesson6.payload.ApiResponse;
import pdp.uz.lesson6.payload.StaffDto;
import pdp.uz.lesson6.repository.FilialRepository;
import pdp.uz.lesson6.repository.RoleRepository;
import pdp.uz.lesson6.repository.StaffRepository;
import pdp.uz.lesson6.security.JwtProvider;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class StaffService {
    @Autowired
    StaffRepository staffRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    FilialRepository filialRepository;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    PasswordEncoder passwordEncoder;

    public ApiResponse addStaff(StaffDto staffDto){
        Optional<Role> optionalRole = roleRepository.findById(staffDto.getRoleId());
        if (!optionalRole.isPresent()){
            return new ApiResponse("Such Role doesnt exist",false);
        }
        Optional<Filial> optionalFilial = filialRepository.findById(staffDto.getFilialId());
        if (!optionalFilial.isPresent()){
            return new ApiResponse("Such Filial doesnt exist",false);
        }
        Staff staff=new Staff();
        staff.setFullName(staffDto.getFullName());
        staff.setUserName(staffDto.getUserName());
        Set<Role> roles=new HashSet<>();
        roles.add(optionalRole.get());
        staff.setRoles(roles);
        staff.setFilial(optionalFilial.get());
        staff.setPosition(staffDto.getPosition());
        staff.setPassword(passwordEncoder.encode(staffDto.getPassword()));
        staffRepository.save(staff);
        return new ApiResponse("Staff added!",true);
    }

    public List<Staff> getStaffList(){
        List<Staff> staffList = staffRepository.findAll();
        return staffList;
    }

    public ApiResponse getStaff(HttpServletRequest httpServletRequest){
        String authorization=httpServletRequest.getHeader("Authorization");
        String token = authorization.substring(7);
        String username = jwtProvider.getUserNameFromToken(token);
        Optional<Staff> optionalStaff = staffRepository.findByUserName(username);
        return optionalStaff.map(staff -> new ApiResponse("=>", true, staff)).orElseGet(() -> new ApiResponse("Staff not found", false));
    }

    public ApiResponse editStaff(String username,StaffDto staffDto){
        Optional<Staff> optionalStaff = staffRepository.findByUserName(username);
        if (!optionalStaff.isPresent()){
            return new ApiResponse("Such Staff doesnt exist",false);
        }
        Optional<Filial> optionalFilial = filialRepository.findById(staffDto.getFilialId());
        if (!optionalFilial.isPresent()){
            return new ApiResponse("Such Filial doesnt exist",false);
        }
        Staff staff = optionalStaff.get();
        staff.setFullName(staffDto.getFullName());
        boolean existsByUserName = staffRepository.existsByUserName(staff.getUsername());
        if (existsByUserName){
            return new ApiResponse("Such staff already exist",false);
        }
        staff.setUserName(staffDto.getUserName());
        staff.setFilial(optionalFilial.get());
        staff.setPosition(staffDto.getPosition());
        staff.setPassword(passwordEncoder.encode(staffDto.getPassword()));
        staffRepository.save(staff);
        return new ApiResponse("Staff edited!",true);
    }


    public ApiResponse deleteStaff(String username){
        Optional<Staff> optionalStaff = staffRepository.findByUserName(username);
        if (!optionalStaff.isPresent()){
            return new ApiResponse("Such staff doesnt exist",false);
        }
        staffRepository.delete(optionalStaff.get());
        return new ApiResponse("Staff deleted",true);
    }
}

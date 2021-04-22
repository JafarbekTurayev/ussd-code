package pdp.uz.lesson6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pdp.uz.lesson6.entity.Filial;
import pdp.uz.lesson6.entity.Role;
import pdp.uz.lesson6.entity.Staff;
import pdp.uz.lesson6.entity.enums.RoleName;
import pdp.uz.lesson6.payload.ApiResponse;
import pdp.uz.lesson6.payload.FilialDto;
import pdp.uz.lesson6.repository.FilialRepository;
import pdp.uz.lesson6.repository.RoleRepository;
import pdp.uz.lesson6.repository.StaffRepository;

import java.util.*;

@Service
public class FilialService {
    @Autowired
    FilialRepository filialRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    StaffRepository staffRepository;



    public ApiResponse addFilial(FilialDto filialDto){
        Filial filial=new Filial();
        filial.setName(filialDto.getName());

        Staff staff=new Staff();
        staff.setUserName(filialDto.getDirectorUserName());
        staff.setFullName(filialDto.getDirectorFullName());
        Set<Role> roleSet=new HashSet<>();
        Role role=new Role(1, RoleName.ROLE_DIRECTOR);
        roleSet.add(role);
        staff.setRoles(roleSet);
        staff.setPosition(filialDto.getDirectorPosition());
        staff.setFilial(filial);
        staff.setPassword(passwordEncoder.encode(filialDto.getDirectorPassword()));
        filial.setDirector(staff);

        List<Staff> staffList=new ArrayList<>();
        for (String staffUsername : filialDto.getStaffUsernames()) {
            Optional<Staff> optionalStaff = staffRepository.findByUserName(staffUsername);
            if (!optionalStaff.isPresent()){
                return new ApiResponse("Such staff doesnt exist",false);
            }
            staffList.add(optionalStaff.get());
        }
        filial.setStaffs(staffList);
        filialRepository.save(filial);
        return new ApiResponse("Filial added",true);
    }

    public ApiResponse editFilial(Integer id,FilialDto filialDto){
        Optional<Filial> optionalFilial = filialRepository.findById(id);
        if (!optionalFilial.isPresent()){
            return new ApiResponse("Such filial doesnt exist",false);
        }
        Filial filial = optionalFilial.get();
        filial.setName(filialDto.getName());
        Staff staff=new Staff();
        staff.setUserName(filialDto.getDirectorUserName());
        staff.setFullName(filialDto.getDirectorFullName());
        Set<Role> roleSet=new HashSet<>();
        Role role=new Role(1, RoleName.ROLE_DIRECTOR);
        roleSet.add(role);
        staff.setRoles(roleSet);
        staff.setPosition(filialDto.getDirectorPosition());
        staff.setFilial(filial);
        staff.setPassword(passwordEncoder.encode(filialDto.getDirectorPassword()));
        filial.setDirector(staff);

        List<Staff> staffList=new ArrayList<>();
        for (String staffUsername : filialDto.getStaffUsernames()) {
            Optional<Staff> optionalStaff = staffRepository.findByUserName(staffUsername);
            if (!optionalStaff.isPresent()){
                return new ApiResponse("Such staff doesnt exist",false);
            }
            staffList.add(optionalStaff.get());
        }
        filial.setStaffs(staffList);
        filialRepository.save(filial);
        return new ApiResponse("Filial edited",true);
    }



    public ApiResponse getFilial(Integer id){
        Optional<Filial> optionalFilial = filialRepository.findById(id);
        return optionalFilial.map(filial -> new ApiResponse("=>", true, filial)).orElseGet(() -> new ApiResponse("Such filial doesnt exist", false));
    }

    public List<Filial> getFilialList(){
        List<Filial> filialList = filialRepository.findAll();
        return filialList;
    }

    public ApiResponse deleteFilial(Integer id){
        Optional<Filial> optionalFilial = filialRepository.findById(id);
        if (!optionalFilial.isPresent()){
            return new ApiResponse("Such Staff doesnt exist",true);
        }
        filialRepository.deleteById(id);
        return new ApiResponse("Staff deleted",true);
    }

    public static void main(String[] args) {
        String number="+998995071840";
        System.out.println(number.substring(4,6));
    }

}

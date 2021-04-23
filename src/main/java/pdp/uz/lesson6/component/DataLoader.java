package pdp.uz.lesson6.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pdp.uz.lesson6.entity.Client;
import pdp.uz.lesson6.entity.Role;
import pdp.uz.lesson6.entity.Staff;
import pdp.uz.lesson6.entity.UssdCode;
import pdp.uz.lesson6.entity.enums.ClientType;
import pdp.uz.lesson6.entity.enums.RoleName;
import pdp.uz.lesson6.repository.ClientRepository;
import pdp.uz.lesson6.repository.RoleRepository;
import pdp.uz.lesson6.repository.StaffRepository;
import pdp.uz.lesson6.repository.UssdCodeRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {
    @Value("${spring.datasource.initialization-mode}")
    private String initialMode;
    @Autowired
    StaffRepository staffRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    UssdCodeRepository ussdCodeRepository;


    @Override
    public void run(String... args) throws Exception {
        if (initialMode.equals("always")) {
            Role directorRole = new Role();
            directorRole.setRoleName(RoleName.ROLE_DIRECTOR);
            Role staff = new Role();
            staff.setRoleName(RoleName.ROLE_STAFF);
            Role manager = new Role();
            manager.setRoleName(RoleName.ROLE_MANAGER);
            roleRepository.save(directorRole);
            roleRepository.save(staff);
            roleRepository.save(manager);


            Staff director = new Staff();
            director.setRoles(Collections.singleton(roleRepository.findByRoleName(RoleName.ROLE_DIRECTOR)));
            director.setUserName("tokhirsam");
            director.setFullName("John Smith");
            director.setPosition("boss");
            director.setPassword(passwordEncoder.encode("123"));
            director.setEnabled(true);
            staffRepository.save(director);

            Client client = new Client();
            client.setClientType(ClientType.USER);
//            client.setPassword(passwordEncoder.encode("123"));
            client.setRoles(Collections.singleton(roleRepository.findByRoleName(RoleName.ROLE_CLIENT)));
            client.setFullName("Karim Benzema");
            client.setPassportNumber("998905556677");
//            client.setEnabled(true);
            clientRepository.save(client);

            List<UssdCode> preAddedUssdCodes = new ArrayList<>();
            UssdCode hisob = new UssdCode();
            hisob.setCode("*100#");
            hisob.setDescription("Hisobni tekshirish");
            preAddedUssdCodes.add(hisob);

            UssdCode internet = new UssdCode();
            internet.setCode("*101#");
            internet.setDescription("Internet qoldig'ini tekshirish");
            preAddedUssdCodes.add(internet);

            UssdCode sms = new UssdCode();
            sms.setCode("*102#");
            sms.setDescription("Sms qoldig'ini tekshirish");
            preAddedUssdCodes.add(sms);

            UssdCode daqiqa = new UssdCode();
            daqiqa.setCode("*103#");
            daqiqa.setDescription("Daqiqa qoldig'ini tekshirish");
            preAddedUssdCodes.add(daqiqa);

            UssdCode internet10Gb = new UssdCode();
            internet10Gb.setCode("*104#");
            internet10Gb.setDescription("10 Gb internet paketini sotib olish");
            preAddedUssdCodes.add(internet10Gb);

            UssdCode minut500 = new UssdCode();
            minut500.setCode("*105#");
            minut500.setDescription("500 minutli paketni paketini sotib olish");
            preAddedUssdCodes.add(minut500);

            ussdCodeRepository.saveAll(preAddedUssdCodes);


        }
    }
}

package pdp.uz.lesson6.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pdp.uz.lesson6.entity.Client;
import pdp.uz.lesson6.entity.Role;
import pdp.uz.lesson6.entity.Staff;
import pdp.uz.lesson6.entity.enums.ClientType;
import pdp.uz.lesson6.entity.enums.RoleName;
import pdp.uz.lesson6.repository.ClientRepository;
import pdp.uz.lesson6.repository.RoleRepository;
import pdp.uz.lesson6.repository.StaffRepository;

import java.util.Collections;

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
            client.setPassword(passwordEncoder.encode("123"));
            client.setRoles(Collections.singleton(roleRepository.findByRoleName(RoleName.ROLE_CLIENT)));
            client.setFullName("Karim Benzema");
            client.setPhoneNumber("998905556677");
            client.setEnabled(true);
            clientRepository.save(client);




        }
    }
}

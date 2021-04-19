package pdp.uz.lesson6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.lesson6.entity.Role;
import pdp.uz.lesson6.entity.enums.RoleName;


public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRoleName(RoleName roleName);
}

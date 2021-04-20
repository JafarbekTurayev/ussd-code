package pdp.uz.lesson6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.lesson6.entity.Staff;

import java.util.Optional;
import java.util.UUID;

public interface StaffRepository extends JpaRepository<Staff, UUID> {
    Optional<Staff> findByUserName(String userName);
    boolean existsByUserName(String userName);
}

package pdp.uz.lesson6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.lesson6.entity.EntertainingService;

import java.util.UUID;


public interface ServiceRepository extends JpaRepository<EntertainingService, UUID> {
    boolean findByName(String name);

}

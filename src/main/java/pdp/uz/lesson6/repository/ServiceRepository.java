package pdp.uz.lesson6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.lesson6.entity.Service;

import java.util.UUID;


public interface ServiceRepository extends JpaRepository<Service, UUID> {

}

package pdp.uz.lesson6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.lesson6.entity.EntertainingService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface ServiceRepository extends JpaRepository<EntertainingService, UUID> {
    Optional<EntertainingService> findByName(String name);
    boolean existsByName(String name);
    List<EntertainingService> findAllByOrderByCountAsc();

}

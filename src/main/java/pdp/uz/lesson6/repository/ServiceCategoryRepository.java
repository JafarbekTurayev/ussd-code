package pdp.uz.lesson6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.lesson6.entity.ServiceCategory;

import java.util.UUID;


public interface ServiceCategoryRepository extends JpaRepository<ServiceCategory, Integer> {
boolean existsByName(String name);
}

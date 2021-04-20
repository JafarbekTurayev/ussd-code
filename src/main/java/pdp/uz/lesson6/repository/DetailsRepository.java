package pdp.uz.lesson6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.lesson6.entity.Details;

import java.util.UUID;


public interface DetailsRepository extends JpaRepository<Details, UUID> {

}

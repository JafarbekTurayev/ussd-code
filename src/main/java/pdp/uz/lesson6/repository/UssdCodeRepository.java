package pdp.uz.lesson6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.lesson6.entity.UssdCode;

import java.util.UUID;


public interface UssdCodeRepository extends JpaRepository<UssdCode, UUID> {

}
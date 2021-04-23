package pdp.uz.lesson6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.lesson6.entity.SimCard;

import java.util.Optional;
import java.util.UUID;


public interface SimCardRepository extends JpaRepository<SimCard, UUID> {
    Optional<SimCard> findBySimCardNumber(String simCardNumber);

    Optional<SimCard> findByCodeAndNumber(String code, String number);

    boolean existsByNumberAndCode(String number, String code);


}

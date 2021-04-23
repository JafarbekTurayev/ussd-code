package pdp.uz.lesson6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.lesson6.entity.Client;

import java.util.Optional;
import java.util.UUID;


public interface ClientRepository extends JpaRepository<Client, UUID> {
    Optional<Client> findByPassportNumber(String passportNumber);

    boolean existsByPassportNumber(String passportNumber);


}

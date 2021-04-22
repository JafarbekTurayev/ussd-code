package pdp.uz.lesson6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.lesson6.entity.Tariff;

import java.util.UUID;


public interface TariffRepository extends JpaRepository<Tariff, UUID> {
    boolean existsByName(String name);
    Tariff findByName(String name);
}

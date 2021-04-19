package pdp.uz.lesson6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.lesson6.entity.Details;
import pdp.uz.lesson6.entity.Filial;

import java.util.UUID;


public interface FilialRepository extends JpaRepository<Filial, UUID> {

}

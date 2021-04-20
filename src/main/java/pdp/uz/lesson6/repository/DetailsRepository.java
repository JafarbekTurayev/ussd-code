package pdp.uz.lesson6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.lesson6.entity.Details;
import pdp.uz.lesson6.entity.SimCard;
import pdp.uz.lesson6.entity.enums.ActionType;

import java.util.List;
import java.util.UUID;


public interface DetailsRepository extends JpaRepository<Details, UUID> {
    List<Details> findAllByActionTypeAndSimCard(ActionType actionType, SimCard simCard);
    List<Details> findAllBySimCard(SimCard simCard);
}

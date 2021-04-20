package pdp.uz.lesson6.payload;

import lombok.Data;
import pdp.uz.lesson6.entity.SimCard;
import pdp.uz.lesson6.entity.enums.ActionType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class DetailDto {
    @Enumerated(EnumType.STRING)
    private ActionType actionType;
    private SimCard simCard; //o'zgarishi mumkin
    private Float amount;
}

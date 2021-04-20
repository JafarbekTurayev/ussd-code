package pdp.uz.lesson6.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pdp.uz.lesson6.entity.enums.ActionType;
import pdp.uz.lesson6.entity.template.AbsEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Details extends AbsEntity {

    @Enumerated(EnumType.STRING)
    private ActionType actionType;

//    @OneToOne
//    private Client client;

    @ManyToOne//bitta simkartaga ko'pgina detaillar to'g'ri keladi
    private SimCard simCard;

    //ayni vaqtda qancha miqdorda ishlatilganligi
    private Float amount;
}

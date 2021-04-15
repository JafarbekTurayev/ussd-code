package pdp.uz.lesson6.entity;

import lombok.Data;
import pdp.uz.lesson6.entity.enums.ActionType;
import pdp.uz.lesson6.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

@Entity
@Data
public class Details extends AbsEntity {

    @Enumerated(EnumType.STRING)
    private ActionType actionType;

    @OneToOne
    private Client client;
    
}

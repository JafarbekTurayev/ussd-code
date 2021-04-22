package pdp.uz.lesson6.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pdp.uz.lesson6.entity.template.AbsEntity;

import javax.persistence.*;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class EntertainingService extends AbsEntity {

    private String name;

    private double price;

    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    private ServiceCategory serviceCategory;

    private Timestamp expiredDate;
    @ManyToOne
    private SimCard simCard;
    private Integer count;

}

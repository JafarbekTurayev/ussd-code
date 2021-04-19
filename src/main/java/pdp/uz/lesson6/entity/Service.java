package pdp.uz.lesson6.entity;

import lombok.Data;
import pdp.uz.lesson6.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Entity
@Data
public class Service extends AbsEntity {

    private String name;

    private double price;

    @ManyToOne
    private ServiceCategory serviceCategory;

    private Timestamp expiredDate;

}

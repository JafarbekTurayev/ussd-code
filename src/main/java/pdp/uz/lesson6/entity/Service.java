package pdp.uz.lesson6.entity;

import lombok.Data;
import pdp.uz.lesson6.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
public class Service extends AbsEntity {

    private String name;

    private double price;

    @ManyToOne
    private ServiceCategory serviceCategory;

    private Timestamp expiredDate;

}

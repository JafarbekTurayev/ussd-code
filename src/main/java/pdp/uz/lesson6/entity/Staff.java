package pdp.uz.lesson6.entity;

import lombok.Data;
import pdp.uz.lesson6.entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
@Data
public class Staff extends AbsEntity {

    @Column(unique = true)
    private String userName;

    private String fullName;

    private String turniket = "ID" + UUID.randomUUID().toString();
    //UUID.randomUUID().toString()

    @ManyToOne
    private Filial filial;

}

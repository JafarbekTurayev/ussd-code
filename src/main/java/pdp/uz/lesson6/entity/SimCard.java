package pdp.uz.lesson6.entity;

import lombok.Data;
import pdp.uz.lesson6.entity.template.AbsEntity;

import javax.persistence.*;

@Entity
@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"code", "number"})})
public class SimCard extends AbsEntity {

    private String name;

    private String code;//90 93 94

    private String number; //7 xonali

    @ManyToOne
    private Client client;

    private boolean active;

    private double balance;

    @ManyToOne
    private Tariff tariff; //

    private boolean tariffIsActive;

    private boolean isCredit; //

}

package pdp.uz.lesson6.entity;

import lombok.Data;
import pdp.uz.lesson6.entity.enums.PacketType;
import pdp.uz.lesson6.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Packet extends AbsEntity {

    @Enumerated(EnumType.STRING)
    private PacketType packetType;

    private String name;

    private int amount;

    private int duration; // 5 kunlik

//    private Timestamp expireDate;
//
//    @Transient
//    private int duration;
//
//    public int getDuration() {
//        return Period.between(this.getCreatedAt(), expireDate).getDays();
//    }

    private boolean isTariff;

    @OneToMany
    private List<Tariff> availableTariffs; //shu tariflarga dostup


}

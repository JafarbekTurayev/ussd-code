package pdp.uz.lesson6.entity;

import lombok.Data;
import pdp.uz.lesson6.entity.enums.ClientType;
import pdp.uz.lesson6.entity.template.AbsEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
public class Tariff extends AbsEntity {

    private String name;


    @OneToMany(fetch = FetchType.EAGER)
    private List<Packet> includedPackets; //paketlar tarif ichidagi 5DAQ 5MIN 12MB

    private double price; //tariff narxi

    private double switchPrice; //o'tish narxi


    private Timestamp expireDate;

    @Enumerated(EnumType.STRING)
    private ClientType clientType;

}

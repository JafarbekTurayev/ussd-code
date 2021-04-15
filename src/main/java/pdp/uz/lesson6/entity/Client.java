package pdp.uz.lesson6.entity;

import lombok.Data;
import pdp.uz.lesson6.entity.enums.ClientType;
import pdp.uz.lesson6.entity.template.AbsEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Client extends AbsEntity {

    private String phoneNumber;

    private String password;

    private String fullName;

    @Enumerated(EnumType.STRING)
    private ClientType clientType;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SimCard> simCardList;

}

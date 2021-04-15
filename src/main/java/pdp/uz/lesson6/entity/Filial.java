package pdp.uz.lesson6.entity;


import lombok.Data;
import pdp.uz.lesson6.entity.template.AbsEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Filial extends AbsEntity {
    private String name;

    @OneToOne
    private Staff director;

    @OneToMany(mappedBy = "filial", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Staff> staffs;
}

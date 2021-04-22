package pdp.uz.lesson6.payload;

import lombok.Data;
import pdp.uz.lesson6.entity.Filial;
import pdp.uz.lesson6.entity.Role;

import javax.persistence.ManyToOne;
import java.util.Set;

@Data
public class StaffDto {
    private String userName;

    private String fullName;

    private Integer roleId;

    private Integer filialId;

    private String position;

    private String password;

}

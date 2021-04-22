package pdp.uz.lesson6.payload;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pdp.uz.lesson6.entity.template.AbsEntity;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
public class ServiceDto extends AbsEntity {
    private String name;

    private double price;

    private Integer serviceCategoryID;

    private Timestamp expiredDate;
    private String categoryName;
}

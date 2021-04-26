package pdp.uz.lesson6.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyingSimCardDto {
    private String code;
    private String number;
    private Double sum;
    private UUID tariffId;
}

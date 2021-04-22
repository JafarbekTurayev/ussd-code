package pdp.uz.lesson6.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimCardDto {
    private String name;
    private String code; //93v94
    private String number;//123456789

}

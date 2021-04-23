package pdp.uz.lesson6.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

    private String passportNumber;
    private String fullName;
    private Integer clientTypeOrdinal;
    private List<BuyingSimCardDto> buyingSimCardDtos;


}

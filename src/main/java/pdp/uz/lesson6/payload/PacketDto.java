package pdp.uz.lesson6.payload;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class PacketDto {
    private Integer packetTypeId;
    @NotNull
    @Column(unique = true)
    private String name;
    private int amount;
    private int cost;
    private int duration;
    private boolean isTariff;
    private List<String> availableTariffs;
}

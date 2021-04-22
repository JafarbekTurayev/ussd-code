package pdp.uz.lesson6.payload;

import lombok.Data;
import pdp.uz.lesson6.entity.enums.PacketType;

import java.util.List;

@Data
public class PacketDtoForClients {
    private PacketType packetType;
    private String name;
    private int amount;
    private int cost;
    private int duration;
    private boolean isTariff;
    private List<String> availableTariffs;
}

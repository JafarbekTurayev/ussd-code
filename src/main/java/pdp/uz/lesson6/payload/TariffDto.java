package pdp.uz.lesson6.payload;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
public class TariffDto {
    @NotNull
    @Column(unique = true)
    private String name; // tarif nomi
    private double price; //tariff narxi
    private double switchPrice; //o'tish narxi
    private int expireDate; // amal qilish muddati
    private int mb;
    private int sms;
    private int min;
    private int mbCost; // mb tugaganda 1mb narxi
    private int smsCost; // sms tugaganda 1sms narxi
    private int minCost; // min tugaganda 1min narxi
    private Integer clientTypeId; // 1==>jismoniy shaxs  2==>yuridik shaxs

}

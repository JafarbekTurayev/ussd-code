package pdp.uz.lesson6.payload;

import lombok.Data;

@Data
public class TariffDtoForClient {
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
}

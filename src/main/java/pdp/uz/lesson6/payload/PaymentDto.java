package pdp.uz.lesson6.payload;

import lombok.Data;
import pdp.uz.lesson6.entity.SimCard;
import pdp.uz.lesson6.entity.enums.PayType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
@Data
public class PaymentDto {
    private String simCardNumber;

    private String payType;

    private double amount;

    private String payerName;

    private String payerId;
}

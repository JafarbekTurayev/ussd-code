package pdp.uz.lesson6.entity;

import lombok.Data;
import pdp.uz.lesson6.entity.enums.PayType;
import pdp.uz.lesson6.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

@Entity
@Data
public class Payment extends AbsEntity {

    @OneToOne
    private SimCard simCard;

    @Enumerated(EnumType.STRING)
    private PayType payType;

    private double amount;

    private String payerName;

    private String payerId;
    //qo'lda chek nomeri

}

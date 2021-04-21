package pdp.uz.lesson6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.lesson6.entity.Payment;

import java.util.List;
import java.util.UUID;


public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    List<Payment> findAllBySimCardNumber(String simCard_number);

}

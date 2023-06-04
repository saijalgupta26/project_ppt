package com.example.mevenproject.repository;




import com.example.mevenproject.dto.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PaymentIdRepository extends MongoRepository<Payment,String> {


    Optional<Payment> findByCustomerIdAndPaymentId(String customerId, String paymentMethodId);
}

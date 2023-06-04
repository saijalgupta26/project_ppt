package com.example.mevenproject.repository;


import com.example.mevenproject.dto.Card;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PaymentMethodRepository extends MongoRepository<Card,String> {
    Optional<Card> findByNumberAndCvc(String number, String cvc);
}

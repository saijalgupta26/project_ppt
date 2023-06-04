package com.example.mevenproject.repository;


import com.example.mevenproject.dto.CustomerDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StripeRepository extends MongoRepository<CustomerDetails,String> {

    Optional<CustomerDetails> findByemail(String email);
}

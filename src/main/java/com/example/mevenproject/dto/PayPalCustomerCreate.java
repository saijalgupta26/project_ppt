package com.example.mevenproject.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Data
@Component
@Slf4j
@Getter
@Setter
public class PayPalCustomerCreate {
    private String firstName;
    private String lastName;
    private String email;
    private String contactNo;
    private String payMethodNonce;

}

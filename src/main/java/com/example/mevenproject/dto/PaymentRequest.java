package com.example.mevenproject.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Data
@Slf4j
public class PaymentRequest {
    private String amount;
/*    private String currency;*/


}

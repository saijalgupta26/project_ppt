package com.example.mevenproject.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Data
@Getter
@Setter
public class PayPalPayment {
    private BigDecimal amount;
    private String purpose;
    private String country;
    private String Addressid;
}

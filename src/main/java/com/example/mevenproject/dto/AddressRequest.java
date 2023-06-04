package com.example.mevenproject.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Data
public class AddressRequest {


    private String line1;
    private String line2;
    private String city;
    private String state;
    private String country;
    private String zipcode;
}

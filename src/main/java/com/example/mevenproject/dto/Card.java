package com.example.mevenproject.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Data
public class Card {
    private String number;
    private String exp_month;
    private String exp_year;

    private String cvc;
    private String id;
    /*private String name;*/


}

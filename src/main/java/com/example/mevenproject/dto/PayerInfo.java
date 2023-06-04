package com.example.mevenproject.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Slf4j
public class PayerInfo {
    private  String email;
    private  String id;
    private String name;
}

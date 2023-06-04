package com.example.mevenproject.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Slf4j
@Data
public class CustomerDetails {
    private String name;
    private String email;
    private  String id;


}

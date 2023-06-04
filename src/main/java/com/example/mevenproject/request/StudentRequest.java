package com.example.mevenproject.request;

import lombok.Data;

@Data
public class StudentRequest {
    private String id;
    private String name;
    private int rollno;
    private String section;
    private String email;
    private String password;
    private String JavaMarks;
    private String SqlMarks;

    private String Totalmarks;

    //@data--tostring and getter and setter
}
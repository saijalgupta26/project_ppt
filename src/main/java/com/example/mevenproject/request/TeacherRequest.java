package com.example.mevenproject.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherRequest {
    private String id;
    private String name;
    private String email;
    private String password;
}

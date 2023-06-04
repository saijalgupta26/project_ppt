package com.example.mevenproject.document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Student {
    @Id
    private String id;
    @NotNull
    private String name;
    @Email
    private String email;
    @Indexed(unique = true)
    private int rollno;
    private String section;
    private String TotalMarks;
    private String JavaMarks;
    private String SqlMarks;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$", message = "password should have uppercase and lowercase and special character.")
    private String password;


}

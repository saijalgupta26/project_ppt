package com.example.mevenproject.serviceTest;

import com.example.mevenproject.document.Student;
import com.example.mevenproject.request.StudentRequest;
import com.example.mevenproject.response.StudentResponse;
import lombok.Data;

@Data
public class MockObjectStudent {
    public static StudentRequest getStudentRequest(){
        StudentRequest studentRequest=new StudentRequest();
        studentRequest.setName("text");
        studentRequest.setRollno(12);
        studentRequest.setEmail("s@S12");
        studentRequest.setSection("AS");
        studentRequest.setPassword("er@eE$23rere");
        return studentRequest;
    }
    public static Student getStudent() {
        Student student=new Student();
        student.setName("text");
        student.setRollno(12);
        student.setEmail("s@S12");
        student.setSection("AS");
        student.setPassword("er@eE$23rere");
        return student;
    }
    public static StudentResponse studentResponse(){
        StudentResponse studentResponse=new StudentResponse();
        studentResponse.setName(getStudent().getName());
        studentResponse.setRollno(getStudent().getRollno());
        studentResponse.setEmail(getStudent().getEmail());
        studentResponse.setSection(getStudent().getSection());
        studentResponse.setPassword(studentResponse.getPassword());
        return studentResponse;
    }
    //create mock method for studentRequest
    //object studenntRequest

}

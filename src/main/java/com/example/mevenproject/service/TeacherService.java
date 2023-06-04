package com.example.mevenproject.service;
import com.example.mevenproject.document.Teacher;
import com.example.mevenproject.exception.TeacherNotFound;
import com.example.mevenproject.request.TeacherRequest;
import com.example.mevenproject.response.TeacherResponse;
import java.util.List;

public interface TeacherService {
    TeacherResponse createTeacher(TeacherRequest teacherRequest);

    List<Teacher> findAllTeacher();

    String delete(String name);

    Teacher updateTeacher(String name, Teacher teacher);

    Teacher findTeacherByName(String name);
    Teacher findTeacherByEmailAndPassword(String email,String password) throws TeacherNotFound;
}

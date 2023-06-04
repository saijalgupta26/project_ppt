package com.example.mevenproject.utill;

import com.example.mevenproject.document.Teacher;
import com.example.mevenproject.request.TeacherRequest;
import com.example.mevenproject.response.TeacherResponse;
import org.springframework.stereotype.Component;

@Component
public class TeacherTransformer {
    public Teacher transformerTeacher(TeacherRequest teacherRequest) {
        Teacher teacher = new Teacher();
        teacher.setName(teacherRequest.getName());
        teacher.setEmail(teacherRequest.getEmail());
        teacher.setPassword(teacherRequest.getPassword());
        return teacher;
    }
    public TeacherResponse prepareTeacher(Teacher teacher) {
        TeacherResponse teacherResponse = new TeacherResponse();
        teacherResponse.setName(teacher.getName());
        teacherResponse.setEmail(teacher.getEmail());
        teacherResponse.setPassword(teacher.getPassword());
        return teacherResponse;
    }
}

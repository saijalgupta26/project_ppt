package com.example.mevenproject.service;

import com.example.mevenproject.document.Teacher;
import com.example.mevenproject.exception.TeacherNotFound;
import com.example.mevenproject.repository.TeacherRepository;
import com.example.mevenproject.request.TeacherRequest;
import com.example.mevenproject.response.TeacherResponse;
import com.example.mevenproject.utill.TeacherTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImp implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private TeacherTransformer teacherTransformer;

    @Override
    public TeacherResponse createTeacher(TeacherRequest teacherRequest) {
        Teacher teacher = teacherTransformer.transformerTeacher(teacherRequest);
        Teacher teacher1 = teacherRepository.save(teacher);
        return teacherTransformer.prepareTeacher(teacher1);
    }

    @Override
    public List<Teacher> findAllTeacher() {
        return teacherRepository.findAll();
    }

    @Override
    public String delete(String name) {
        Teacher teacherByName = findTeacherByName(name);
        teacherRepository.delete(teacherByName);
        return "delete teacher successfully";
    }

    @Override
    public Teacher updateTeacher(String name, Teacher teacher) {
        Teacher teacherByName = findTeacherByName(name);
        teacherByName.setName(teacher.getName());
        teacherByName.setEmail(teacher.getEmail());
        return teacherRepository.save(teacherByName);
    }

    @Override
    public Teacher findTeacherByName(String name) {
        Optional<Teacher> teacher = teacherRepository.findTeacherByName(name);
        return teacher.orElse(null);
    }

    @Override
    public Teacher findTeacherByEmailAndPassword(String email, String password) throws TeacherNotFound {
        Optional<Teacher> teacher = teacherRepository.findTeacherByEmailAndPassword(email, password);
        if(ObjectUtils.isEmpty(teacher))
        {
            throw new TeacherNotFound("teacher not Found");
        }
        return teacher.get();
    }


}

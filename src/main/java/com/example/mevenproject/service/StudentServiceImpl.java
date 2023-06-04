package com.example.mevenproject.service;

import com.example.mevenproject.document.Student;
import com.example.mevenproject.exception.StudentNotFound;
import com.example.mevenproject.repository.StudentRepository;
import com.example.mevenproject.request.StudentRequest;
import com.example.mevenproject.response.StudentResponse;
import com.example.mevenproject.utill.Trasnformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
   @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private Trasnformer trasnformer;

    public StudentResponse createStudent(StudentRequest studentRequest) {
        //insert student data
        if (ObjectUtils.isEmpty(studentRequest) ) {
            return null;
        }
        Student student = trasnformer.transformStudent(studentRequest);
        Student student1 = studentRepository.save(student);
        return trasnformer.prepareStudentResponse(student1);
    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }


    @Override
    public Student findStudenyByRollnoAndSection(int rollno, String section)  {
        Optional<Student> student = studentRepository.findByRollnoAndSection(rollno,section);
        return student.orElse(null);
    }
    @Override
    public List<Student> findStudenyBysection(String section)  {
        return studentRepository.findBysection(section);
    }

    @Override
    public Student patchStudent(int rollno, String section,Student student) throws StudentNotFound {
        Student studenyByRollno = findStudenyByRollnoAndSection(rollno,section);
        if(ObjectUtils.isEmpty(studenyByRollno))
        {
            throw  new StudentNotFound("student not found exception");
        }
        studenyByRollno.setEmail(student.getEmail());
        studenyByRollno.setTotalMarks(student.getTotalMarks());
        return studentRepository.save(studenyByRollno);

    }


    @Override
    public Student updateStudent(int rollno,String section, Student student) throws StudentNotFound {
        Student studenyByRollno = findStudenyByRollnoAndSection(rollno,section);
        if(ObjectUtils.isEmpty(studenyByRollno))
        {
            throw  new StudentNotFound("student not found exception");
        }
        studenyByRollno.setName(student.getName());
        studenyByRollno.setSection(student.getSection());
        studenyByRollno.setEmail(student.getEmail());
        studenyByRollno.setTotalMarks(student.getTotalMarks());
        studenyByRollno.setJavaMarks(student.getJavaMarks());
        studenyByRollno.setSqlMarks(student.getSqlMarks());

        return studentRepository.save(studenyByRollno);
    }


    @Override
    public String deleteStudent(int rollno,String section) throws StudentNotFound {
        Student studeny = findStudenyByRollnoAndSection(rollno,section);
        if(ObjectUtils.isEmpty(studeny))
        {
            throw  new StudentNotFound("student not found");
        }
        studentRepository.delete(studeny);
        return "student delete successfully";
    }

    @Override
    public Student findStudentByEmailAndPassword(String email, String password) throws StudentNotFound {
        Optional<Student> student = studentRepository.findByEmailAndPassword(email, password);

        if(ObjectUtils.isEmpty(student)) {
            throw new StudentNotFound("student not found");
        }
        return student.get();
    }

}
//add logger for logging


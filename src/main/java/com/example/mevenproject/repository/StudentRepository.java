package com.example.mevenproject.repository;
import com.example.mevenproject.document.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends MongoRepository<Student,String>
{
    Optional<Student> findByRollnoAndSection(int rollno,String section);
    Optional<Student> findByEmailAndPassword(String email,String password);
    List<Student> findBysection(String section);

}

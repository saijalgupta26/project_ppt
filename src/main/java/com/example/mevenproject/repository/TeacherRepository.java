package com.example.mevenproject.repository;
import com.example.mevenproject.document.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TeacherRepository  extends MongoRepository<Teacher,String> {
    Optional<Teacher> findTeacherByName(String name);
    Optional<Teacher> findTeacherByEmailAndPassword(String email,String password);
}

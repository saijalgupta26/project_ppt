package com.example.mevenproject.serviceTest;

import com.example.mevenproject.document.Student;
import com.example.mevenproject.exception.StudentNotFound;
import com.example.mevenproject.repository.StudentRepository;
import com.example.mevenproject.response.StudentResponse;
import com.example.mevenproject.service.StudentServiceImpl;
import com.example.mevenproject.utill.Trasnformer;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private StudentServiceImpl studentService;
    @Mock
    private Trasnformer trasnformer;


    @Test
    public void createStudentTest() {
        Mockito.when(trasnformer.transformStudent(Mockito.any())).thenReturn(MockObjectStudent.getStudent());
        Mockito.when(studentRepository.save(Mockito.any())).thenReturn(MockObjectStudent.getStudent());
        Mockito.when(trasnformer.prepareStudentResponse(Mockito.any())).thenReturn(MockObjectStudent.studentResponse());
        StudentResponse response= studentService.createStudent(MockObjectStudent.getStudentRequest());
        Assertions.assertEquals(response.getName(),MockObjectStudent.getStudent().getName());

    }
    @Test
    public void createStudentTest_InvalidRequest() {

        assertThatCode(() -> {
            studentService.createStudent(null);
        }).doesNotThrowAnyException();
    }

    @Test
    public void deleteStudentTest() {
        //Mockito.when(studentRepository.findByRollno(Mockito.anyInt())).thenReturn(Optional.of(MockObject.getStudent()));
        assertThatCode(()->studentService.deleteStudent(Mockito.anyInt(),Mockito.any())).isInstanceOf(StudentNotFound.class);
    }
    @Test
    public void deleteStudentTest_Success() {
        Mockito.when(studentRepository.findByRollnoAndSection(Mockito.anyInt(),Mockito.any())).thenReturn(Optional.of(MockObjectStudent.getStudent()));
        assertThatCode(() -> studentService.deleteStudent(Mockito.anyInt(),Mockito.any())).doesNotThrowAnyException();
    }
    @Test
    public void updateStudentTest_Success() {
        Mockito.when(studentRepository.findByRollnoAndSection(Mockito.anyInt(),Mockito.any())).thenReturn(Optional.of(MockObjectStudent.getStudent()));
        assertThatCode(()->studentService.updateStudent(Mockito.anyInt(),Mockito.any(), MockObjectStudent.getStudent())).doesNotThrowAnyException();
    }
    @Test
    public void updateStudentTest_InvalidRequest() {
        //Mockito.when(studentRepository.findByRollno(Mockito.anyInt())).thenReturn(Optional.of(MockObject.getStudent()));
        assertThatCode(()->studentService.updateStudent(Mockito.anyInt(),Mockito.any(), MockObjectStudent.getStudent())).isInstanceOf(StudentNotFound.class);

    }

    @Test
    public void getAllStudentTest() {
        List<Student> student = List.of(MockObjectStudent.getStudent());
        Mockito.when(studentRepository.findAll()).thenReturn(student);
        assertThatCode(() -> studentService.getAllStudent()).doesNotThrowAnyException();
    }
    @Test
    public void getStudentByRollnoTest(){
        Mockito.when(studentRepository.findByRollnoAndSection(Mockito.anyInt(),Mockito.any())).thenReturn(Optional.of(MockObjectStudent.getStudent()));
        assertThatCode(()->studentService.findStudenyByRollnoAndSection(Mockito.anyInt(),Mockito.any())).doesNotThrowAnyException();

    }
}
//serila
//cloning
//clonable
//string buillder
////java 8
//

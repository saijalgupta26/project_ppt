package com.example.mevenproject.controller;

import com.braintreegateway.AddressRequest;
import com.braintreegateway.CustomerRequest;
import com.example.mevenproject.document.Student;
import com.example.mevenproject.dto.CustomerDetails;
import com.example.mevenproject.dto.Data;
import com.example.mevenproject.exception.StudentNotFound;
import com.example.mevenproject.request.StudentRequest;
import com.example.mevenproject.response.StudentResponse;
import com.example.mevenproject.service.StudentService;
import com.stripe.exception.StripeException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;


import java.util.Currency;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @PostMapping("/createStudent")
    public ResponseEntity<?> createStudent(@Valid @RequestBody StudentRequest studentRequest) {
        StudentResponse student1 = studentService.createStudent(studentRequest);
        return new ResponseEntity<>(student1, HttpStatus.CREATED); //create student data by using postman
    }

    @GetMapping("/getAllStudent")
    public ResponseEntity<?> getAllStudent() {
        List<Student> allStudent = studentService.getAllStudent();
        return new ResponseEntity<>(allStudent, HttpStatus.OK); // get all student
    }

    @GetMapping("/getStudentByRollnoAndSection")
    public ResponseEntity<?> getStudentByRollnoAndSection(@RequestParam int rollno,@RequestParam String section) {
        ResponseEntity<?> entity;
        Student student = studentService.findStudenyByRollnoAndSection(rollno,section);
        if (!ObjectUtils.isEmpty(student)) {
            entity = new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            entity = new ResponseEntity<>("Student Not Found", HttpStatus.NOT_FOUND);
        }
        return entity;// find student by rollno and section
    }



    @GetMapping("/getStudentsBySection")
    public ResponseEntity<?> getStudentsBySection( String section) {
        ResponseEntity<?> entity;
        List<Student> student = studentService.findStudenyBysection(section);
        if (!ObjectUtils.isEmpty(student)) {
            entity = new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            entity = new ResponseEntity<>("Student Not Found", HttpStatus.NOT_FOUND);
        }
        return entity;  //get all student those have same section
    }

    @RequestMapping("/deleteStudent/{rollno}/{section}")
    public ResponseEntity<?> deleteStudent(@PathVariable int rollno,@PathVariable String section) throws StudentNotFound {
        String student = studentService.deleteStudent(rollno,section);
        return new ResponseEntity<>(student, HttpStatus.OK);  //delete Student by rollno and section
    }

//        //blog reading third party api

    @RequestMapping("/login")
    public String login(Model model) {
        return "studentLogin"; //student login page
    }
    @RequestMapping("/addStudent")
    public ModelAndView addStudent(StudentRequest studentRequest)
    {
        ModelAndView modelAndView=new ModelAndView("teacherWelcome");
        studentService.createStudent(studentRequest);
        modelAndView.addObject("students",studentService.getAllStudent());
        return modelAndView;
        //add student data and return back to teacher  welcome page
    }

    @RequestMapping("/loginPage")
    public ModelAndView studentWelcome(StudentRequest studentRequest) throws StudentNotFound {
        Student student = studentService.findStudentByEmailAndPassword(studentRequest.getEmail(), studentRequest.getPassword());
        if(!ObjectUtils.isEmpty(student))
        {
            ModelAndView modelAndView=new ModelAndView("studentWelcome");
            modelAndView.addObject("studentData",student);
            modelAndView.addObject("students",studentService.findStudenyBysection(student.getSection()));
            return modelAndView;
            // student login page if id password is correct then return to student welcome page
        }
        else {
            return new ModelAndView("studentLogin");
            // if password mismatch than return again login page
        }
    }

    @RequestMapping("/updateStudent/{rollno}/{section}")
    public ModelAndView updateStudent1(@PathVariable int rollno,@PathVariable String section)
    {
        Student student = studentService.findStudenyByRollnoAndSection(rollno, section);
        ModelAndView modelAndView=new ModelAndView("update-student");
        modelAndView.addObject("updatingStudent",student);
        return modelAndView;// find student  by using roll and section and repopulated student form

    }

    @RequestMapping ("/updateStudent1")
    public ModelAndView updateData(Student student) throws StudentNotFound {
        studentService.updateStudent(student.getRollno(),student.getSection(), student);
        ModelAndView modelAndView =new ModelAndView("teacherWelcome");
        modelAndView.addObject("students",studentService.getAllStudent());
        return modelAndView; //update student details
    }
    @GetMapping("/1")
    public ResponseEntity<?> getTeacherInStudent()
    {
        String url="http://localhost:9192/teacher/getAll";
        RestTemplate restTemplate=new RestTemplate();
        Object[] countries=restTemplate.getForObject(url,Object[].class);

        return new ResponseEntity<>(countries,HttpStatus.OK); //use external api
    }
    @RequestMapping("/register")
    public ModelAndView register(){
        ModelAndView modelAndView=new ModelAndView("registration");
        return modelAndView;

    }

    @PostMapping("/countries")
    public ResponseEntity<?> countries()
    {
        Data data =new Data();
        data.setCustomerDetails(new CustomerDetails());

       String url="http://localhost:9123/stripePayment";
       String response="success";

        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<Data> dataResponseEntity = restTemplate.postForEntity(url, data, Data.class);



        return new ResponseEntity<>(dataResponseEntity,HttpStatus.OK);
    }
    @PatchMapping("updateEmail/{rollno}/{section}")
    public ResponseEntity<?> patchStudent(@PathVariable int rollno,@PathVariable String section,@RequestBody Student student) throws StudentNotFound {


        Student student1 = studentService.patchStudent(rollno, section, student);

        return new ResponseEntity<>(student1,HttpStatus.ACCEPTED);
    }
    /*@GetMapping("/se")
    public ResponseEntity<?> findStudent() throws StudentNotFound {
        Student student = studentService.findStudentByEmailAndPassword("student1@123","Student1@123");
        if(!ObjectUtils.isEmpty(student))
        {
            return  new ResponseEntity<>(student,HttpStatus.OK);

        }
        else
        {
            return null;
        }*/

   /* }*/


}
//delete

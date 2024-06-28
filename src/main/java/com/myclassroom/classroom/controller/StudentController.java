package com.myclassroom.classroom.controller;

import com.myclassroom.classroom.pojo.StudentRegistrationReq;
import com.myclassroom.classroom.pojo.StudentRegistrationRes;
import com.myclassroom.classroom.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("student")
public class StudentController {

    final StudentService studentService;
    // Constructor Injections
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("student_registration")
    public ResponseEntity<StudentRegistrationRes> registerStudent(@Valid @RequestBody StudentRegistrationReq studentRegistrationReq){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.registerStudent(studentRegistrationReq));
    }
}

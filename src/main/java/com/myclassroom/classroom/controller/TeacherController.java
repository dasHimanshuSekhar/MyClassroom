package com.myclassroom.classroom.controller;

import com.myclassroom.classroom.pojo.TeacherRegistrationReq;
import com.myclassroom.classroom.pojo.TeacherRegistrationRes;
import com.myclassroom.classroom.services.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("teacher")
public class TeacherController {
    TeacherService teacherService;
    // Constructor Injections
    public TeacherController(TeacherService teacherService){
        this.teacherService = teacherService;
    }

    @PostMapping("teacher_registration")
    public ResponseEntity<TeacherRegistrationRes> teacherRegistration(@RequestBody @Valid TeacherRegistrationReq teacherRegistrationReq){
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.registerTeacher(teacherRegistrationReq));
    }


}

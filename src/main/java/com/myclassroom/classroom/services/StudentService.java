package com.myclassroom.classroom.services;


import com.myclassroom.classroom.pojo.StudentRegistrationReq;
import com.myclassroom.classroom.pojo.StudentRegistrationRes;

public interface StudentService {
    StudentRegistrationRes registerStudent(StudentRegistrationReq studentRegistrationReq);
}

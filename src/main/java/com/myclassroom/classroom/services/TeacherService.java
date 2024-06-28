package com.myclassroom.classroom.services;

import com.myclassroom.classroom.pojo.TeacherRegistrationReq;
import com.myclassroom.classroom.pojo.TeacherRegistrationRes;
import org.springframework.stereotype.Service;

public interface TeacherService {
    TeacherRegistrationRes registerTeacher(TeacherRegistrationReq teacherRegistrationReq);
}

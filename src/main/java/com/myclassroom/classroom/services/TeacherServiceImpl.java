package com.myclassroom.classroom.services;

import com.myclassroom.classroom.entity.Teacher;
import com.myclassroom.classroom.pojo.TeacherRegistrationReq;
import com.myclassroom.classroom.pojo.TeacherRegistrationRes;
import com.myclassroom.classroom.repository.TeacherRepository;
import com.myclassroom.classroom.utils.GeneralConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TeacherServiceImpl implements TeacherService {
    Logger logger = LoggerFactory.getLogger(TeacherServiceImpl.class);
    private final TeacherRepository teacherRepository;
    
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public TeacherRegistrationRes registerTeacher(TeacherRegistrationReq teacherRegistrationReq) {
        try{
            // Reference to the teacher collection

            // Check for duplicate user ID

            // Check for duplicate email ID

            // Check for duplicate mobile number

            /* Teacher Entity Preparation */
            Teacher teacher = new Teacher();
            teacher.setTeacherId(teacherRegistrationReq.getTeacherId());
            teacher.setPassword(teacherRegistrationReq.getPassword());
            teacher.setFirstName(teacherRegistrationReq.getFirstName());
            teacher.setLastName(teacherRegistrationReq.getLastName());
            teacher.setEmailId(teacherRegistrationReq.getEmailId());
            teacher.setMobileNumber(teacherRegistrationReq.getMobileNumber());
            teacher.setCreatedDate(LocalDateTime.now());
            teacher.setUpdatedDate(LocalDateTime.now());

            // Save Admin
            teacherRepository.save(teacher);
            logger.info("Teacher successfully registered: {}", teacher.getTeacherId());

        } catch (Exception e) {
            logger.error("teacher_registration :: {} :: Exception: '{}'", GeneralConstants.DATABASE_EXCEPTION, e.getMessage());
            return new TeacherRegistrationRes(null,-1, GeneralConstants.DATABASE_EXCEPTION);
        }
        return new TeacherRegistrationRes(teacherRegistrationReq.getTeacherId(), 0, "New Teacher Onboarded Successfully");
    }
}

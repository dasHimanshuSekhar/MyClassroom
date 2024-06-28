package com.myclassroom.classroom.services;

import com.myclassroom.classroom.enity.Teacher;
import com.myclassroom.classroom.pojo.AdminRegistrationRes;
import com.myclassroom.classroom.pojo.TeacherRegistrationReq;
import com.myclassroom.classroom.pojo.TeacherRegistrationRes;
import com.myclassroom.classroom.repo.TeacherRepository;
import com.myclassroom.classroom.utils.GeneralConstants;
import net.bytebuddy.asm.Advice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    Logger logger = LoggerFactory.getLogger(TeacherServiceImpl.class);

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public TeacherRegistrationRes registerTeacher(TeacherRegistrationReq teacherRegistrationReq) {
        try{
            // Duplicate Checked
            Optional<Teacher> teacherOptional = teacherRepository.findById(teacherRegistrationReq.getTeacherId());
            // Admin
            if(teacherOptional.isPresent()){
                logger.warn("teacher_registration :: Teacher User Id Already Exists :: userId: {}", teacherRegistrationReq.getTeacherId());
                return new TeacherRegistrationRes(null,-1, "Teacher Already exist with userId: " + teacherRegistrationReq.getTeacherId());
            }
            // Email Id
            if(teacherRepository.existsByEmailId(teacherRegistrationReq.getEmailId())){
                logger.warn("teacher_registration :: Email Id Already Exists :: userId: {}", teacherRegistrationReq.getTeacherId());
                return new TeacherRegistrationRes(null,-1, "Teacher Already exist with this Email Id: " + teacherRegistrationReq.getEmailId());
            }
            // Mobile Number
            if(teacherRepository.existsByMobileNumber(teacherRegistrationReq.getMobileNumber())){
                logger.warn("teacher_registration :: Email Id Already Exists :: userId: {}", teacherRegistrationReq.getTeacherId());
                return new TeacherRegistrationRes(null,-1, "Teacher Already exist with this Email Id: " + teacherRegistrationReq.getEmailId());
            }

            /* Teacher Entity Preparation */
            Teacher teacher = new Teacher();
            teacher.setTeacherId(teacherRegistrationReq.getTeacherId());
            teacher.setPassword(teacherRegistrationReq.getMobileNumber());
            teacher.setFirstName(teacherRegistrationReq.getFirstName());
            teacher.setLastName(teacherRegistrationReq.getLastName());
            teacher.setEmailId(teacherRegistrationReq.getEmailId());
            teacher.setMobileNumber(teacherRegistrationReq.getMobileNumber());
            teacher.setCreatedDate(LocalDateTime.now());
            teacher.setUpdatedDate(LocalDateTime.now());
            teacherRepository.save(teacher);

        } catch (Exception e) {
            logger.error("teacher_registration :: {} :: Exception: '{}'", GeneralConstants.DATABASE_EXCEPTION, e.getMessage());
            return new TeacherRegistrationRes(null,-1, GeneralConstants.DATABASE_EXCEPTION);
        }
        return new TeacherRegistrationRes(teacherRegistrationReq.getTeacherId(), 0, "New Teacher Onboarded Successfully");
    }
}

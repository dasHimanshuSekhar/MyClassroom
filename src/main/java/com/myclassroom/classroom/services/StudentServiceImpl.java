package com.myclassroom.classroom.services;

import com.myclassroom.classroom.enity.Student;
import com.myclassroom.classroom.pojo.StudentRegistrationReq;
import com.myclassroom.classroom.pojo.StudentRegistrationRes;
import com.myclassroom.classroom.repo.StudentRepository;
import com.myclassroom.classroom.utils.GeneralConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    final private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentRegistrationRes registerStudent(StudentRegistrationReq studentRegistrationReq) {
        try{
            // Duplicate Check
            Optional<Student> studentOptional = studentRepository.findById(studentRegistrationReq.getStudentId());
            // Student
            if(studentOptional.isPresent()){
                logger.warn("student_registration :: Student User Id Already Exists :: userId: {}", studentRegistrationReq.getStudentId());
                return new StudentRegistrationRes(null,-1, "Student Already exist with userId: " + studentRegistrationReq.getStudentId());
            }
            // Email Id
            if(studentRepository.existsByEmailId(studentRegistrationReq.getEmailId())){
                logger.warn("student_registration :: Email Id '{}' Already Exists :: userId: {}", studentRegistrationReq.getEmailId(), studentRegistrationReq.getStudentId());
                return new StudentRegistrationRes(null,-1, "Student Already exist with this Email Id: " + studentRegistrationReq.getEmailId());
            }
            // Mobile Number
            if(studentRepository.existsByMobileNumber(studentRegistrationReq.getMobileNumber())){
                logger.warn("student_registration :: Mobile Number '{}' Already Exists :: userId: {}", studentRegistrationReq.getMobileNumber(), studentRegistrationReq.getStudentId());
                return new StudentRegistrationRes(null,-1, "Student Already exist with this Mobile Number: " + studentRegistrationReq.getMobileNumber());
            }

            /* Teacher Entity Preparation */
            Student student = new Student();
            student.setStudentId(studentRegistrationReq.getStudentId());
            student.setPassword(studentRegistrationReq.getPassword());
            student.setFirstName(studentRegistrationReq.getFirstName());
            student.setLastName(studentRegistrationReq.getLastName());
            student.setEmailId(studentRegistrationReq.getEmailId());
            student.setMobileNumber(studentRegistrationReq.getMobileNumber());
            student.setCreatedDate(LocalDateTime.now());
            student.setUpdatedDate(LocalDateTime.now());
            studentRepository.save(student);

        } catch (Exception e) {
            logger.error("student_registration :: {} :: Exception: '{}'", GeneralConstants.DATABASE_EXCEPTION, e.getMessage());
            return new StudentRegistrationRes(null,-1, GeneralConstants.DATABASE_EXCEPTION);
        }
        return new StudentRegistrationRes(studentRegistrationReq.getStudentId(), 0, "New Student Onboarded Successfully");
    }
}

package com.myclassroom.classroom.services;

import com.myclassroom.classroom.entity.Student;
import com.myclassroom.classroom.pojo.StudentRegistrationReq;
import com.myclassroom.classroom.pojo.StudentRegistrationRes;
import com.myclassroom.classroom.repository.StudentRepository;
import com.myclassroom.classroom.utils.GeneralConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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

            // Reference to the students collection

            // Check for duplicate user ID

            // Check for duplicate email ID

            // Check for duplicate mobile number

            /* Teacher Entity Preparation */
            Student student = new Student();
            student.setStudentId(studentRegistrationReq.getStudentId());
            student.setPassword(studentRegistrationReq.getPassword());
            student.setFirstName(studentRegistrationReq.getFirstName());
            student.setLastName(studentRegistrationReq.getLastName());
            student.setEmailId(studentRegistrationReq.getEmailId());
            student.setMobileNumber(studentRegistrationReq.getMobileNumber());
            student.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
            student.setUpdatedDate(Timestamp.valueOf(LocalDateTime.now()));

            // Save Admin to Firestore
            studentRepository.save(student);
            logger.info("Student successfully registered: {}", student.getStudentId());

        } catch (Exception e) {
            logger.error("student_registration :: {} :: Exception: '{}'", GeneralConstants.DATABASE_EXCEPTION, e.getMessage());
            return new StudentRegistrationRes(null,-1, GeneralConstants.DATABASE_EXCEPTION);
        }
        return new StudentRegistrationRes(studentRegistrationReq.getStudentId(), 0, "New Student Onboarded Successfully");
    }
}

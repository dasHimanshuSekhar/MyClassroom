package com.myclassroom.classroom.services;

import com.google.cloud.firestore.*;
import com.myclassroom.classroom.enity.Student;
import com.myclassroom.classroom.pojo.AdminRegistrationRes;
import com.myclassroom.classroom.pojo.StudentRegistrationReq;
import com.myclassroom.classroom.pojo.StudentRegistrationRes;
import com.myclassroom.classroom.utils.GeneralConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    final private Firestore firestore;

    public StudentServiceImpl(Firestore firestore){
        this.firestore = firestore;
    }

    @Override
    public StudentRegistrationRes registerStudent(StudentRegistrationReq studentRegistrationReq) {
        try{

            // Reference to the students collection
            CollectionReference studentCollection = firestore.collection("students");

            // Check for duplicate user ID
            DocumentReference docRef = studentCollection.document(studentRegistrationReq.getStudentId());
            if (docRef.get().get().exists()) {
                logger.warn("student_registration :: Student User Id Already Exists :: userId: {}", studentRegistrationReq.getStudentId());
                return new StudentRegistrationRes(null,-1, "Student Already exist with userId: " + studentRegistrationReq.getStudentId());
            }

            // Check for duplicate email ID
            Query emailQuery = studentCollection.whereEqualTo("emailId", studentRegistrationReq.getEmailId());
            QuerySnapshot emailSnapshot = emailQuery.get().get();
            if(!emailSnapshot.isEmpty()){
                logger.warn("student_registration :: Email Id '{}' Already Exists :: userId: {}", studentRegistrationReq.getEmailId(), studentRegistrationReq.getStudentId());
                return new StudentRegistrationRes(null,-1, "Student Already exist with this Email Id: " + studentRegistrationReq.getEmailId());
            }

            // Check for duplicate mobile number
            Query mobileQuery = studentCollection.whereEqualTo("mobileNumber", studentRegistrationReq.getMobileNumber());
            QuerySnapshot mobileSnapshot = mobileQuery.get().get();
            if (!mobileSnapshot.isEmpty()) {
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
            student.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
            student.setUpdatedDate(Timestamp.valueOf(LocalDateTime.now()));

            // Save Admin to Firestore
            WriteResult writeResult = studentCollection.document(studentRegistrationReq.getStudentId()).set(student).get();
            logger.info("Student successfully registered: {}", writeResult.getUpdateTime());

        } catch (Exception e) {
            logger.error("student_registration :: {} :: Exception: '{}'", GeneralConstants.DATABASE_EXCEPTION, e.getMessage());
            return new StudentRegistrationRes(null,-1, GeneralConstants.DATABASE_EXCEPTION);
        }
        return new StudentRegistrationRes(studentRegistrationReq.getStudentId(), 0, "New Student Onboarded Successfully");
    }
}

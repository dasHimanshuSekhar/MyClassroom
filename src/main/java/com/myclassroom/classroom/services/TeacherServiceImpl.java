package com.myclassroom.classroom.services;

import com.google.cloud.firestore.*;
import com.myclassroom.classroom.enity.Teacher;
import com.myclassroom.classroom.pojo.AdminRegistrationRes;
import com.myclassroom.classroom.pojo.TeacherRegistrationReq;
import com.myclassroom.classroom.pojo.TeacherRegistrationRes;
import com.myclassroom.classroom.utils.GeneralConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {
    Logger logger = LoggerFactory.getLogger(TeacherServiceImpl.class);
    private final Firestore firestore;
    
    public TeacherServiceImpl(Firestore firestore) {
        this.firestore = firestore;
    }

    @Override
    public TeacherRegistrationRes registerTeacher(TeacherRegistrationReq teacherRegistrationReq) {
        try{
            // Reference to the teacher collection
            CollectionReference teacherCollection = firestore.collection("teachers");

            // Check for duplicate user ID
            DocumentReference docRef = teacherCollection.document(teacherRegistrationReq.getTeacherId());
            if (docRef.get().get().exists()) {
                logger.warn("teacher_registration :: Teacher User Id Already Exists :: userId: {}", teacherRegistrationReq.getTeacherId());
                return new TeacherRegistrationRes(null,-1, "Teacher Already exist with userId: " + teacherRegistrationReq.getTeacherId());
            }

            // Check for duplicate email ID
            Query emailQuery = teacherCollection.whereEqualTo("emailId", teacherRegistrationReq.getEmailId());
            QuerySnapshot emailSnapshot = emailQuery.get().get();
            if(!emailSnapshot.isEmpty()){
                logger.warn("teacher_registration :: Email Id '{}' Already Exists :: userId: {}", teacherRegistrationReq.getEmailId(), teacherRegistrationReq.getTeacherId());
                return new TeacherRegistrationRes(null,-1, "Teacher Already exist with this Email Id: " + teacherRegistrationReq.getEmailId());
            }

            // Check for duplicate mobile number
            Query mobileQuery = teacherCollection.whereEqualTo("mobileNumber", teacherRegistrationReq.getMobileNumber());
            QuerySnapshot mobileSnapshot = mobileQuery.get().get();
            if (!mobileSnapshot.isEmpty()) {
                logger.warn("teacher_registration :: Mobile number '{}' Already Exists :: userId: {}", teacherRegistrationReq.getMobileNumber(), teacherRegistrationReq.getTeacherId());
                return new TeacherRegistrationRes(null,-1, "Teacher Already exist with this mobile Number: " + teacherRegistrationReq.getMobileNumber());
            }


            /* Teacher Entity Preparation */
            Teacher teacher = new Teacher();
            teacher.setTeacherId(teacherRegistrationReq.getTeacherId());
            teacher.setPassword(teacherRegistrationReq.getPassword());
            teacher.setFirstName(teacherRegistrationReq.getFirstName());
            teacher.setLastName(teacherRegistrationReq.getLastName());
            teacher.setEmailId(teacherRegistrationReq.getEmailId());
            teacher.setMobileNumber(teacherRegistrationReq.getMobileNumber());
            teacher.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
            teacher.setUpdatedDate(Timestamp.valueOf(LocalDateTime.now()));

            // Save Admin to Firestore
            WriteResult writeResult = teacherCollection.document(teacherRegistrationReq.getTeacherId()).set(teacher).get();
            logger.info("Teacher successfully registered: {}", writeResult.getUpdateTime());

        } catch (Exception e) {
            logger.error("teacher_registration :: {} :: Exception: '{}'", GeneralConstants.DATABASE_EXCEPTION, e.getMessage());
            return new TeacherRegistrationRes(null,-1, GeneralConstants.DATABASE_EXCEPTION);
        }
        return new TeacherRegistrationRes(teacherRegistrationReq.getTeacherId(), 0, "New Teacher Onboarded Successfully");
    }
}

package com.myclassroom.classroom.services;

import com.google.cloud.firestore.*;
import com.myclassroom.classroom.enity.Admin;
import com.myclassroom.classroom.pojo.AdminRegistrationReq;
import com.myclassroom.classroom.pojo.AdminRegistrationRes;
import com.myclassroom.classroom.utils.GeneralConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class AdminServiceImpl implements AdminService{
    Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
    private final Firestore firestore;


    public AdminServiceImpl(Firestore firestore) {
        this.firestore = firestore;
    }

    @Override
    public AdminRegistrationRes registerAdmin(AdminRegistrationReq adminRegistrationReq) {
        try{
            // Reference to the admins collection
            CollectionReference adminCollection = firestore.collection("admins");

            // Check for duplicate user ID
            DocumentReference docRef = adminCollection.document(adminRegistrationReq.getAdminId());
            if (docRef.get().get().exists()) {
                logger.warn("admin_registration :: Admin User Id Already Exists :: userId: {}", adminRegistrationReq.getAdminId());
                return new AdminRegistrationRes(null, -1, "Admin already exists with userId: " + adminRegistrationReq.getAdminId());
            }

            // Check for duplicate email ID
            Query emailQuery = adminCollection.whereEqualTo("emailId", adminRegistrationReq.getEmailId());
            QuerySnapshot emailSnapshot = emailQuery.get().get();
            if(!emailSnapshot.isEmpty()){
                logger.error("Duplicate email ID found: {}", adminRegistrationReq.getEmailId());
                return new AdminRegistrationRes(null, -1, "Email ID already exists");
            }

            // Check for duplicate mobile number
            Query mobileQuery = adminCollection.whereEqualTo("mobileNumber", adminRegistrationReq.getMobileNumber());
            QuerySnapshot mobileSnapshot = mobileQuery.get().get();
            if (!mobileSnapshot.isEmpty()) {
                logger.error("Duplicate mobile number found: {}", adminRegistrationReq.getMobileNumber());
                return new AdminRegistrationRes(null, -1, "Mobile number already exists");
            }


            /* Admin Entity Preparation */
            Admin admin = new Admin();
            admin.setAdminId(adminRegistrationReq.getAdminId());
            admin.setPassword(adminRegistrationReq.getPassword()); // need to encrypt later
            admin.setFirstName(adminRegistrationReq.getFirstName());
            admin.setLastName(adminRegistrationReq.getLastName());
            admin.setEmailId(adminRegistrationReq.getEmailId());
            admin.setMobileNumber(adminRegistrationReq.getMobileNumber());
            admin.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
            admin.setUpdatedDate(Timestamp.valueOf(LocalDateTime.now()));

            // Save Admin to Firestore
            WriteResult writeResult = adminCollection.document(adminRegistrationReq.getAdminId()).set(admin).get();
            logger.info("Admin successfully registered: {}", writeResult.getUpdateTime());

        } catch (Exception e) {
            logger.error("admin_registration :: {} :: Exception: '{}'", GeneralConstants.DATABASE_EXCEPTION, e.getMessage());
            return new AdminRegistrationRes(null, -1, GeneralConstants.DATABASE_EXCEPTION);
        }

        return new AdminRegistrationRes(adminRegistrationReq.getAdminId(),0,"New Admin Onboarded Successfully");
    }
}

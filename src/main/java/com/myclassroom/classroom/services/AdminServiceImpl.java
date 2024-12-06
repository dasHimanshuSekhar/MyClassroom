package com.myclassroom.classroom.services;

import com.myclassroom.classroom.entity.Admin;
import com.myclassroom.classroom.pojo.AdminRegistrationReq;
import com.myclassroom.classroom.pojo.AdminRegistrationRes;
import com.myclassroom.classroom.pojo.projection.AdminOnboardVerification;
import com.myclassroom.classroom.repository.AdminRepository;
import com.myclassroom.classroom.utils.GeneralConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService{
    Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
    final private AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public AdminRegistrationRes registerAdmin(AdminRegistrationReq adminRegistrationReq) {
        try{
            // Check for duplicate user ID
//            Optional<AdminOnboardVerification> admin = adminRepository.verifyAdminOnboardDetails(adminRegistrationReq.getAdminId(), adminRegistrationReq.getMobileNumber(), adminRegistrationReq.getEmailId());
//            if(admin.isPresent()){
//
//            }
            // Check for duplicate email ID

            // Check for duplicate mobile number

            /* Admin Entity Preparation */
            Admin admin = new Admin();
            admin.setAdminId(adminRegistrationReq.getAdminId());
            admin.setPassword(adminRegistrationReq.getPassword()); // need to encrypt later
            admin.setFirstName(adminRegistrationReq.getFirstName());
            admin.setLastName(adminRegistrationReq.getLastName());
            admin.setEmailId(adminRegistrationReq.getEmailId());
            admin.setMobileNumber(adminRegistrationReq.getMobileNumber());
            admin.setCreatedDate(LocalDateTime.now());
            admin.setUpdatedDate(LocalDateTime.now());

            // Save Admin
            adminRepository.save(admin);
            logger.info("Admin successfully registered: {}", admin.getAdminId());

        } catch (Exception e) {
            logger.error("admin_registration :: {} :: Exception: '{}'", GeneralConstants.DATABASE_EXCEPTION, e.getMessage());
            return new AdminRegistrationRes(null, -1, GeneralConstants.DATABASE_EXCEPTION);
        }

        return new AdminRegistrationRes(adminRegistrationReq.getAdminId(),0,"New Admin Onboarded Successfully");
    }
}

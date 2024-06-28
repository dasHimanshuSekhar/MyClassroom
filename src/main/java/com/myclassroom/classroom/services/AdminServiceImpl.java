package com.myclassroom.classroom.services;

import com.myclassroom.classroom.enity.Admin;
import com.myclassroom.classroom.pojo.AdminRegistrationReq;
import com.myclassroom.classroom.pojo.AdminRegistrationRes;
import com.myclassroom.classroom.repo.AdminRepository;
import com.myclassroom.classroom.utils.GeneralConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService{
    Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
    private final AdminRepository adminRepository;


    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public AdminRegistrationRes registerAdmin(AdminRegistrationReq adminRegistrationReq) {
        try{
            // Duplicate Checked
            Optional<Admin> adminOptional = adminRepository.findById(adminRegistrationReq.getAdminId());
            // Admin
            if(adminOptional.isPresent()){
                logger.warn("admin_registration :: Admin User Id Already Exists :: userId: {}", adminRegistrationReq.getAdminId());
                return new AdminRegistrationRes(null,-1, "Admin Already exist with userId: " + adminRegistrationReq.getAdminId());
            }
            // Email Id
            if(adminRepository.existsByEmailId(adminRegistrationReq.getEmailId())){
                logger.warn("admin_registration :: Email Id Already Exists :: userId: {}", adminRegistrationReq.getAdminId());
                return new AdminRegistrationRes(null,-1, "Admin Already exist with this Email Id: " + adminRegistrationReq.getEmailId());
            }
            // Mobile Number
            if(adminRepository.existsByMobileNumber(adminRegistrationReq.getMobileNumber())){
                logger.warn("admin_registration :: Mobile Number Already Exists :: userId: {}", adminRegistrationReq.getAdminId());
                return new AdminRegistrationRes(null,-1, "Admin Already exist with this Mobile Number: " + adminRegistrationReq.getMobileNumber());
            }

            /* Admin Entity Preparation */
            Admin admin = new Admin();
            admin.setAdminId(adminRegistrationReq.getAdminId());
            admin.setPassword(adminRegistrationReq.getPassword()); // need to encrypt later
            admin.setFirstName(adminRegistrationReq.getFirstName());
            admin.setLastName(adminRegistrationReq.getLastName());
            admin.setEmailId(adminRegistrationReq.getEmailId());
            admin.setMobileNumber(adminRegistrationReq.getMobileNumber());
            admin.setUpdatedDate(LocalDateTime.now());
            admin.setCreatedDate(LocalDateTime.now());
            adminRepository.save(admin);

        } catch (Exception e) {
            logger.error("admin_registration :: {} :: Exception: '{}'", GeneralConstants.DATABASE_EXCEPTION, e.getMessage());
            return new AdminRegistrationRes(null, -1, GeneralConstants.DATABASE_EXCEPTION);
        }

        return new AdminRegistrationRes(adminRegistrationReq.getAdminId(),0,"New Admin Onboarded Successfully");
    }
}

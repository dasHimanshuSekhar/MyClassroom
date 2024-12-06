package com.myclassroom.classroom.repository;

import com.myclassroom.classroom.entity.Admin;
import com.myclassroom.classroom.pojo.projection.AdminOnboardVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, String> {

    // admin id, email id, mob no
    @Query(value = "SELECT a.admin_id AS adminId, a.email_id AS emailId, a.mobile_number AS mobileNumber" +
            "FROM admin a WHERE a.admin_id = ?1 OR a.email_id = ?2 OR a.mobile_number = ?3", nativeQuery = true)
    Optional<AdminOnboardVerification> verifyAdminOnboardDetails(String adminId, Long mobileNumber, String emailId);
}

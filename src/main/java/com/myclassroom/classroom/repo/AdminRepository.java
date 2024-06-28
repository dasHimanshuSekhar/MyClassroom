package com.myclassroom.classroom.repo;

import com.myclassroom.classroom.enity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

    boolean existsByEmailId(String emailId);
    boolean existsByMobileNumber(String mobileNumber);
}

package com.myclassroom.classroom.repo;

import com.myclassroom.classroom.enity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, String> {
    boolean existsByEmailId(String emailId);
    boolean existsByMobileNumber(Long mobileNumber);
}

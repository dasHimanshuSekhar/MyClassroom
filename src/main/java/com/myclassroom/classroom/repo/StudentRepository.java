package com.myclassroom.classroom.repo;

import com.myclassroom.classroom.enity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {
    boolean existsByEmailId(String emailId);
    boolean existsByMobileNumber(Long mobileNumber);
}

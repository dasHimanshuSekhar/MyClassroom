package com.myclassroom.classroom.repository;

import com.myclassroom.classroom.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {
}

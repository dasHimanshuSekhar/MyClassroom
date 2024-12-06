package com.myclassroom.classroom.repository;

import com.myclassroom.classroom.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, String> {
}

package com.myclassroom.classroom.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "admin")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Admin {
    @Id
    @Column(unique = true, nullable = false)
    private String adminId;
    private String password;
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String emailId;
    @Column(unique = true, nullable = false)
    private Long mobileNumber;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Student> students;
    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Teacher> teachers;
}

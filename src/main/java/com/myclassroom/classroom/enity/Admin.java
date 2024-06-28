package com.myclassroom.classroom.enity;

import javax.persistence.*;
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
    private List<Student> students;
    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<Teacher> teachers;
}

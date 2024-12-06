package com.myclassroom.classroom.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "teacher")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @Id
    @Column(unique = true, nullable = false)
    private String teacherId;
    private String password;
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private Long mobileNumber;
    @Column(unique = true, nullable = false)
    private String emailId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @ManyToMany
    @JoinTable(
            name = "join_teacher_student",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students;
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<Subject> subjects;
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<AssignmentQuestion> assignmentQuestions;
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<AssignmentAnswer> assignmentAnswers;
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<ExamAnswer> examAnswers;
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<ExamDetails> examDetails;
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<ExamQuestion> examQuestions;
    @ManyToOne
    private Admin admin;
}

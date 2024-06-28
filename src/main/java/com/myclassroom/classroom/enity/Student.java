package com.myclassroom.classroom.enity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @Column(unique = true, nullable = false)
    private String studentId;
    private String password;
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private Long mobileNumber;
    @Column(unique = true, nullable = false)
    private String emailId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @ManyToMany(mappedBy = "students", cascade = CascadeType.ALL)
    private List<Teacher> teachers;
    @ManyToMany
    @JoinTable(
            name = "join_student_subject",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<Subject> subjects;
    @ManyToMany
    @JoinTable(
            name = "join_student_assignment_question",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "assignment_question_id")
    )
    private List<AssignmentQuestion> assignmentQuestions;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<AssignmentAnswer> assignmentAnswers;
    @ManyToMany
    @JoinTable(
            name = "join_student_exam_details",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "exam_details_id")
    )
    private List<ExamDetails> examDetails;
    @ManyToMany
    @JoinTable(
            name = "join_student_exam_question",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "exam_questions_id")
    )
    private List<ExamQuestion> examQuestions;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<ExamAnswer> examAnswers;
    @ManyToOne
    private Admin admin;
}

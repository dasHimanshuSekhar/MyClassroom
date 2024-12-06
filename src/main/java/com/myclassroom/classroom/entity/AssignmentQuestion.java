package com.myclassroom.classroom.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "assignment_question")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentQuestion {
    @Id
    @Column(unique = true, nullable = false)
    private Long assignmentId;
    private String assignmentName;
    private String fileUrl;
    private Boolean closed;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @ManyToOne
    private Teacher teacher;
    @ManyToMany(mappedBy = "assignmentQuestions", cascade = CascadeType.ALL)
    private List<Student> students;
    @ManyToOne
    private Subject subject;
    @OneToMany(mappedBy = "assignmentQuestion", cascade = CascadeType.ALL)
    private List<AssignmentAnswer> assignmentAnswers;
}

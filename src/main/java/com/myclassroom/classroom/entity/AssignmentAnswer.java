package com.myclassroom.classroom.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "assignment_answer")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentAnswer {
    @Id
    @Column(unique = true, nullable = false)
    private Long answerId;
    @Column(unique = true, nullable = false)
    private String fileUrl;
    private String result;
    private Boolean submitted;
    private LocalDateTime submittedDate;
    private LocalDateTime updatedDate;

    @ManyToOne
    private Teacher teacher;
    @ManyToOne
    private Student student;
    @ManyToOne
    private AssignmentQuestion assignmentQuestion;
}

package com.myclassroom.classroom.enity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "subject")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subject {
    @Id
    @Column(unique = true, nullable = false)
    private Long subjectId;
    @Column(unique = true, nullable = false)
    private String subjectName;

    @ManyToMany(mappedBy = "subjects", cascade = CascadeType.ALL)
    private List<Student> students;
    @ManyToOne
    private Teacher teacher;
    @OneToOne
    private ExamDetails examDetails;
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<AssignmentQuestion> assignmentQuestions;
}

package com.myclassroom.classroom.enity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Table(name = "exam_details")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamDetails {
    @Id
    @Column(unique = true, nullable = false)
    private Long examId;
    private String examName;
    private LocalTime duration;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean objective;
    private Boolean subjective;
    private Boolean individualDuration;

    @ManyToOne
    private Teacher teacher;
    @ManyToMany(mappedBy = "examDetails", cascade = CascadeType.ALL)
    private List<Student> students;
    @OneToMany(mappedBy = "examDetail", cascade = CascadeType.ALL)
    private List<ExamQuestion> examQuestions;
    @OneToOne(mappedBy = "examDetails", cascade = CascadeType.ALL)
    private Subject subject;
}

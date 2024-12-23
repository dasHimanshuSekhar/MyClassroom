package com.myclassroom.classroom.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "exam_answer")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamAnswer {
    @Id
    @Column(unique = true, nullable = false)
    private Long answerId;
    private Integer questionNumber;
    private String answer;
    private String attachmentUrl;

    @ManyToOne
    private Teacher teacher;
    @ManyToOne
    private Student student;
    @ManyToOne
    private ExamQuestion examQuestion;
}

package com.myclassroom.classroom.enity;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;

@Table(name = "exam_question")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class ExamQuestion {
    @Id
    @Column(unique = true, nullable = false)
    private Long questionId;
    private Integer questionNumber;
    private String question;
    @Type(type = "json")
    @Column(name = "options", columnDefinition = "json")
    private List<String> options;

    @ManyToOne
    private Teacher teacher;
    @ManyToMany(mappedBy = "examQuestions", cascade = CascadeType.ALL)
    private List<Student> students;
    @OneToMany(mappedBy = "examQuestion", cascade = CascadeType.ALL)
    private List<ExamAnswer> examAnswers;
    @ManyToOne
    private ExamDetails examDetail;
}
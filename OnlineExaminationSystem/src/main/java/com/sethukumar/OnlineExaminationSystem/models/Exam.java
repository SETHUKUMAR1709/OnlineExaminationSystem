package com.sethukumar.OnlineExaminationSystem.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer totalMarks;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @OneToMany(mappedBy="exam", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Question> questionList;

    @OneToMany(mappedBy="exam", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ExamAttempt> examAttemptList;

}

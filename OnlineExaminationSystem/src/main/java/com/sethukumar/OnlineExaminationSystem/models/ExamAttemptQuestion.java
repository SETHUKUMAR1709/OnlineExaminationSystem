package com.sethukumar.OnlineExaminationSystem.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExamAttemptQuestion {
    @EmbeddedId
    private ExamAttemptQuestionId id;
    private String answer;
    private Integer score;

    @ManyToOne
    @MapsId("attemptId")
    @JoinColumn(name = "attempt_id")
    private ExamAttempt attempt;

    @ManyToOne
    @MapsId("questionId")
    @JoinColumn(name = "question_id")
    private Question question;

}

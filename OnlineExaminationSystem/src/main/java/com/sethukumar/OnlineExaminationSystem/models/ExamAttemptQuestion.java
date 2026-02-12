package com.sethukumar.OnlineExaminationSystem.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "answer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExamAttemptQuestion {

    @EmbeddedId
    private ExamAttemptQuestionId id;

    @Column(columnDefinition = "TEXT")
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

    @ManyToOne
    @JoinColumn(name = "graded_by")
    private User gradedBy;
}

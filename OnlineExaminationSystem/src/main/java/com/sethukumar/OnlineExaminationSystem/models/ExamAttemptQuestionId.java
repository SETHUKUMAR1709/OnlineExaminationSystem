package com.sethukumar.OnlineExaminationSystem.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ExamAttemptQuestionId {
    private Long attemptId;
    private Long questionId;
}

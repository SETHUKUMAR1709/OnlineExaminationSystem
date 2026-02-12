package com.sethukumar.OnlineExaminationSystem.services.interfaces;

import com.sethukumar.OnlineExaminationSystem.models.ExamAttemptQuestion;
import com.sethukumar.OnlineExaminationSystem.dto.ScorePerQuestionDTO;

import java.util.List;

public interface ExamAttemptQuestionService {
    ExamAttemptQuestion submitAnswer(ExamAttemptQuestion answer);

    ExamAttemptQuestion gradeAnswer(Long attemptId, Long questionId, Integer score, Long gradedById);

    List<ScorePerQuestionDTO> getScoresByAttempt(Long attemptId);

    List<Object[]> getMostDifficultQuestions();
}

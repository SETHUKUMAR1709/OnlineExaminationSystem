package com.sethukumar.OnlineExaminationSystem.services.interfaces;

import com.sethukumar.OnlineExaminationSystem.models.ExamAttemptQuestion;
import com.sethukumar.OnlineExaminationSystem.dto.ScorePerQuestionDTO;

import java.util.List;

public interface ExamAttemptQuestionService {
    ExamAttemptQuestion submitAnswer(ExamAttemptQuestion answer);
    List<ScorePerQuestionDTO> getScoresByAttempt(Long attemptId);
    List<Object[]> getMostDifficultQuestions();
}


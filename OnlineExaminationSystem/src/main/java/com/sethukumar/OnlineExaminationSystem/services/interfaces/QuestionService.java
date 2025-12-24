package com.sethukumar.OnlineExaminationSystem.services.interfaces;

import com.sethukumar.OnlineExaminationSystem.models.Question;

import java.util.List;

public interface QuestionService {
    Question createQuestion(Question question);
    Question getQuestion(Long id);
    List<Question> getQuestionsByExam(Long examId);
    Question updateQuestion(Long id, Question question);
    void deleteQuestion(Long id);
    List<Question> getUnansweredQuestions(Long attemptId);
}


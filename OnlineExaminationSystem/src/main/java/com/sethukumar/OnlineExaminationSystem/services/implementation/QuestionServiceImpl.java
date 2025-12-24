package com.sethukumar.OnlineExaminationSystem.services.implementation;

import com.sethukumar.OnlineExaminationSystem.models.Question;
import com.sethukumar.OnlineExaminationSystem.repository.QuestionRepository;
import com.sethukumar.OnlineExaminationSystem.services.interfaces.QuestionService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository repo;

    public QuestionServiceImpl(QuestionRepository repo) {
        this.repo = repo;
    }

    public Question createQuestion(Question question) {
        return repo.save(question);
    }

    public Question getQuestion(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
    }

    public List<Question> getQuestionsByExam(Long examId) {
        return repo.findByExamId(examId);
    }

    public Question updateQuestion(Long id, Question question) {
        Question existing = getQuestion(id);
        existing.setText(question.getText());
        existing.setMaxMarks(question.getMaxMarks());
        return repo.save(existing);
    }

    public void deleteQuestion(Long id) {
        repo.deleteById(id);
    }
    @Override
    public List<Question> getUnansweredQuestions(Long attemptId) {
        return repo.findUnansweredQuestions(attemptId);
    }

}

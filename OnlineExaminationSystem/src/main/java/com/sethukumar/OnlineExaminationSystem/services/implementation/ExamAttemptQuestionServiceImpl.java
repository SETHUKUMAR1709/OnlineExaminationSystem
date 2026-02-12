package com.sethukumar.OnlineExaminationSystem.services.implementation;

import com.sethukumar.OnlineExaminationSystem.models.ExamAttempt;
import com.sethukumar.OnlineExaminationSystem.models.ExamAttemptQuestion;
import com.sethukumar.OnlineExaminationSystem.models.ExamAttemptQuestionId;
import com.sethukumar.OnlineExaminationSystem.models.QuestionType;
import com.sethukumar.OnlineExaminationSystem.models.User;
import com.sethukumar.OnlineExaminationSystem.repository.ExamAttemptQuestionRepository;
import com.sethukumar.OnlineExaminationSystem.repository.ExamAttemptRepository;
import com.sethukumar.OnlineExaminationSystem.repository.UserRepository;
import com.sethukumar.OnlineExaminationSystem.services.interfaces.ExamAttemptQuestionService;
import com.sethukumar.OnlineExaminationSystem.dto.ScorePerQuestionDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ExamAttemptQuestionServiceImpl implements ExamAttemptQuestionService {

    private final ExamAttemptQuestionRepository repo;
    private final ExamAttemptRepository examAttemptRepository;
    private final UserRepository userRepository;

    public ExamAttemptQuestionServiceImpl(
            ExamAttemptQuestionRepository repo,
            ExamAttemptRepository examAttemptRepository,
            UserRepository userRepository) {
        this.repo = repo;
        this.examAttemptRepository = examAttemptRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ExamAttemptQuestion submitAnswer(ExamAttemptQuestion answer) {
        ExamAttemptQuestion saved = repo.save(answer);

        // Auto-grade MCQ and FILL_IN_THE_BLANK answers
        if (saved.getQuestion() != null && saved.getQuestion().getType() != null) {
            QuestionType type = saved.getQuestion().getType();
            if (type == QuestionType.MCQ || type == QuestionType.FILL_IN_THE_BLANK) {
                String correctAnswer = saved.getQuestion().getCorrectAnswer();
                if (correctAnswer != null && correctAnswer.equalsIgnoreCase(saved.getAnswer())) {
                    saved.setScore(saved.getQuestion().getMaxMarks());
                } else {
                    saved.setScore(0);
                }
                saved = repo.save(saved);
            }
        }

        // Recalculate total score on the attempt
        Long attemptId = answer.getAttempt().getId();
        Integer total = examAttemptRepository.calculateTotalScore(attemptId);
        ExamAttempt attempt = examAttemptRepository.findById(attemptId).orElseThrow();
        attempt.setTotalScore(total != null ? total : 0);

        return saved;
    }

    @Override
    public ExamAttemptQuestion gradeAnswer(Long attemptId, Long questionId, Integer score, Long gradedById) {
        ExamAttemptQuestionId id = new ExamAttemptQuestionId(attemptId, questionId);
        ExamAttemptQuestion answer = repo.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Answer not found for attempt " + attemptId + " question " + questionId));

        User grader = userRepository.findById(gradedById)
                .orElseThrow(() -> new RuntimeException("Grader not found with id: " + gradedById));

        answer.setScore(score);
        answer.setGradedBy(grader);
        ExamAttemptQuestion saved = repo.save(answer);

        // Recalculate total score on the attempt
        Integer total = examAttemptRepository.calculateTotalScore(attemptId);
        ExamAttempt attempt = examAttemptRepository.findById(attemptId).orElseThrow();
        attempt.setTotalScore(total != null ? total : 0);

        return saved;
    }

    @Override
    public List<ScorePerQuestionDTO> getScoresByAttempt(Long attemptId) {
        return repo.findScoresByAttempt(attemptId);
    }

    @Override
    public List<Object[]> getMostDifficultQuestions() {
        return repo.findMostDifficultQuestions();
    }
}

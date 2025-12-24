package com.sethukumar.OnlineExaminationSystem.services.implementation;

import com.sethukumar.OnlineExaminationSystem.models.ExamAttempt;
import com.sethukumar.OnlineExaminationSystem.models.ExamAttemptQuestion;
import com.sethukumar.OnlineExaminationSystem.repository.ExamAttemptQuestionRepository;
import com.sethukumar.OnlineExaminationSystem.repository.ExamAttemptRepository;
import com.sethukumar.OnlineExaminationSystem.services.interfaces.ExamAttemptQuestionService;
import com.sethukumar.OnlineExaminationSystem.dto.ScorePerQuestionDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ExamAttemptQuestionServiceImpl  implements ExamAttemptQuestionService {

    private final ExamAttemptQuestionRepository repo;
    private final ExamAttemptRepository examAttemptRepository;

    public ExamAttemptQuestionServiceImpl(
            ExamAttemptQuestionRepository repo,
            ExamAttemptRepository examAttemptRepository
    ) {
        this.repo = repo;
        this.examAttemptRepository = examAttemptRepository;
    }

    @Override
    public ExamAttemptQuestion submitAnswer(ExamAttemptQuestion answer) {

        ExamAttemptQuestion saved = repo.save(answer);

        Long attemptId = answer.getAttempt().getId();

        Integer total =
                examAttemptRepository.calculateTotalScore(attemptId);

        ExamAttempt attempt = examAttemptRepository
                .findById(attemptId)
                .orElseThrow();

        attempt.setTotalScore(total);

        return saved;
    }

    @Override
    public List<ScorePerQuestionDTO> getScoresByAttempt(Long attemptId) {
        return repo.findScoresByAttempt(attemptId);
    }


    public List<Object[]> getMostDifficultQuestions() {
        return repo.findMostDifficultQuestions();
    }
}

package com.sethukumar.OnlineExaminationSystem.services.implementation;

import com.sethukumar.OnlineExaminationSystem.dto.ExamResultDTO;
import com.sethukumar.OnlineExaminationSystem.models.Exam;
import com.sethukumar.OnlineExaminationSystem.models.ExamAttempt;
import com.sethukumar.OnlineExaminationSystem.models.Role;
import com.sethukumar.OnlineExaminationSystem.models.User;
import com.sethukumar.OnlineExaminationSystem.repository.ExamAttemptRepository;
import com.sethukumar.OnlineExaminationSystem.repository.ExamRepository;
import com.sethukumar.OnlineExaminationSystem.repository.UserRepository;
import com.sethukumar.OnlineExaminationSystem.services.interfaces.ExamAttemptService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ExamAttemptServiceImpl implements ExamAttemptService {

    private final ExamAttemptRepository repo;
    private final ExamRepository examRepository;
    private final UserRepository userRepository;

    public ExamAttemptServiceImpl(
            ExamAttemptRepository repo,
            ExamRepository examRepository,
            UserRepository userRepository) {
        this.repo = repo;
        this.examRepository = examRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ExamAttempt createAttempt(ExamAttempt attempt) {
        return repo.save(attempt);
    }

    @Override
    public ExamAttempt startAttempt(Long examId, Long studentId) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found with id: " + examId));

        if (!exam.isPublished()) {
            throw new RuntimeException("Cannot attempt an unpublished exam");
        }

        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));

        if (student.getRole() != Role.STUDENT) {
            throw new RuntimeException("Only students can attempt exams");
        }

        ExamAttempt attempt = new ExamAttempt();
        attempt.setExam(exam);
        attempt.setStudent(student);
        attempt.setAttemptDate(LocalDateTime.now());
        attempt.setTotalScore(0);
        attempt.setGraded(false);

        return repo.save(attempt);
    }

    @Override
    public ExamAttempt getAttempt(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Attempt not found with id: " + id));
    }

    @Override
    public List<ExamAttempt> getAttemptsByStudent(Long studentId) {
        return repo.findByStudentId(studentId);
    }

    @Override
    public void deleteAttempt(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<User> getHighestScoringStudents(Long examId) {
        return repo.findHighestScoringStudents(examId);
    }

    @Override
    public List<User> getFailedStudents(Long examId, int passMark) {
        return repo.findFailedStudents(examId, passMark);
    }

    @Override
    public List<ExamResultDTO> getResultsByExam(Long examId) {
        return repo.findResultsByExam(examId);
    }

    @Override
    public long countAttemptsByExam(Long examId) {
        return repo.countByExamId(examId);
    }

    @Override
    public ExamAttempt gradeAttempt(Long attemptId) {
        ExamAttempt attempt = getAttempt(attemptId);

        Integer totalScore = repo.calculateTotalScore(attemptId);
        attempt.setTotalScore(totalScore != null ? totalScore : 0);
        attempt.setGraded(true);

        return repo.save(attempt);
    }

    @Override
    public List<ExamAttempt> getUngradedAttempts(Long examId) {
        return repo.findByExamIdAndGradedFalse(examId);
    }
}

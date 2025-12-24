package com.sethukumar.OnlineExaminationSystem.services.implementation;

import com.sethukumar.OnlineExaminationSystem.dto.ExamResultDTO;
import com.sethukumar.OnlineExaminationSystem.models.ExamAttempt;
import com.sethukumar.OnlineExaminationSystem.models.Student;
import com.sethukumar.OnlineExaminationSystem.repository.ExamAttemptRepository;
import com.sethukumar.OnlineExaminationSystem.services.interfaces.ExamAttemptService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ExamAttemptServiceImpl implements ExamAttemptService {

    private final ExamAttemptRepository repo;

    public ExamAttemptServiceImpl(ExamAttemptRepository repo) {
        this.repo = repo;
    }

    public ExamAttempt createAttempt(ExamAttempt attempt) {
        return repo.save(attempt);
    }

    public ExamAttempt getAttempt(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Attempt not found"));
    }

    public List<ExamAttempt> getAttemptsByStudent(Long studentId) {
        return repo.findByStudentId(studentId);
    }

    public void deleteAttempt(Long id) {
        repo.deleteById(id);
    }

    public List<Student> getHighestScoringStudents(Long examId) {
        return repo.findHighestScoringStudents(examId);
    }

    public List<Student> getFailedStudents(Long examId, int passMark) {
        return repo.findFailedStudents(examId, passMark);
    }
    @Override
    public List<ExamResultDTO> getResultsByExam(Long examId) {
        return repo.findResultsByExam(examId);
    }


    public long countAttemptsByExam(Long examId) {
        return repo.countByExamId(examId);
    }
}


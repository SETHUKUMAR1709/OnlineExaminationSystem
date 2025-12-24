package com.sethukumar.OnlineExaminationSystem.services.implementation;

import com.sethukumar.OnlineExaminationSystem.models.Exam;
import com.sethukumar.OnlineExaminationSystem.repository.ExamRepository;
import com.sethukumar.OnlineExaminationSystem.services.interfaces.ExamService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ExamServiceImpl implements ExamService {

    private final ExamRepository repo;

    public ExamServiceImpl(ExamRepository repo) {
        this.repo = repo;
    }

    public Exam createExam(Exam exam) {
        return repo.save(exam);
    }

    public Exam getExam(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found"));
    }

    public List<Exam> getAllExams() {
        return repo.findAll();
    }

    public Exam updateExam(Long id, Exam exam) {
        Exam existing = getExam(id);
        existing.setTitle(exam.getTitle());
        existing.setTotalMarks(exam.getTotalMarks());
        return repo.save(existing);
    }

    public void deleteExam(Long id) {
        repo.deleteById(id);
    }

    public List<Exam> getExamsByTeacher(Long teacherId) {
        return repo.findByTeacherId(teacherId);
    }

    public List<Exam> getExamsWithAverageScoreGreaterThan(double score) {
        return repo.findExamsWithAverageScoreGreaterThan(score);
    }
}


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

    @Override
    public Exam createExam(Exam exam) {
        exam.setPublished(false);
        return repo.save(exam);
    }

    @Override
    public Exam getExam(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found with id: " + id));
    }

    @Override
    public List<Exam> getAllExams() {
        return repo.findAll();
    }

    @Override
    public Exam updateExam(Long id, Exam exam) {
        Exam existing = getExam(id);
        existing.setTitle(exam.getTitle());
        existing.setTotalMarks(exam.getTotalMarks());
        existing.setTotalQuestions(exam.getTotalQuestions());
        return repo.save(existing);
    }

    @Override
    public void deleteExam(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<Exam> getExamsByTeacher(Long teacherId) {
        return repo.findByTeacherId(teacherId);
    }

    @Override
    public List<Exam> getExamsWithAverageScoreGreaterThan(double score) {
        return repo.findExamsWithAverageScoreGreaterThan(score);
    }

    @Override
    public Exam publishExam(Long id) {
        Exam exam = getExam(id);
        exam.setPublished(true);
        return repo.save(exam);
    }

    @Override
    public List<Exam> getPublishedExams() {
        return repo.findByPublishedTrue();
    }
}

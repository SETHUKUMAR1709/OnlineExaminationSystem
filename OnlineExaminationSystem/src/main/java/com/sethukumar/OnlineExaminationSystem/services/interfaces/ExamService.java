package com.sethukumar.OnlineExaminationSystem.services.interfaces;

import com.sethukumar.OnlineExaminationSystem.models.Exam;

import java.util.List;

public interface ExamService {
    Exam createExam(Exam exam);
    Exam getExam(Long id);
    List<Exam> getAllExams();
    Exam updateExam(Long id, Exam exam);
    void deleteExam(Long id);

    List<Exam> getExamsByTeacher(Long teacherId);
    List<Exam> getExamsWithAverageScoreGreaterThan(double score);
}


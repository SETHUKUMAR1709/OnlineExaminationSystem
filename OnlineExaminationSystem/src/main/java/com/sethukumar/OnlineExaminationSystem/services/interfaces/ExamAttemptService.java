package com.sethukumar.OnlineExaminationSystem.services.interfaces;

import com.sethukumar.OnlineExaminationSystem.dto.ExamResultDTO;
import com.sethukumar.OnlineExaminationSystem.models.ExamAttempt;
import com.sethukumar.OnlineExaminationSystem.models.Student;

import java.util.List;

public interface ExamAttemptService {
    ExamAttempt createAttempt(ExamAttempt attempt);
    ExamAttempt getAttempt(Long id);
    List<ExamAttempt> getAttemptsByStudent(Long studentId);
    void deleteAttempt(Long id);
    List<ExamResultDTO> getResultsByExam(Long examId);
    List<Student> getHighestScoringStudents(Long examId);
    List<Student> getFailedStudents(Long examId, int passMark);
    long countAttemptsByExam(Long examId);
}

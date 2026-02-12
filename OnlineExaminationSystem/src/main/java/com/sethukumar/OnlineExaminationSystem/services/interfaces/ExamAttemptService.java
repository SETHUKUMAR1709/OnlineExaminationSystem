package com.sethukumar.OnlineExaminationSystem.services.interfaces;

import com.sethukumar.OnlineExaminationSystem.dto.ExamResultDTO;
import com.sethukumar.OnlineExaminationSystem.models.ExamAttempt;
import com.sethukumar.OnlineExaminationSystem.models.User;

import java.util.List;

public interface ExamAttemptService {
    ExamAttempt createAttempt(ExamAttempt attempt);

    ExamAttempt startAttempt(Long examId, Long studentId);

    ExamAttempt getAttempt(Long id);

    List<ExamAttempt> getAttemptsByStudent(Long studentId);

    void deleteAttempt(Long id);

    List<ExamResultDTO> getResultsByExam(Long examId);

    List<User> getHighestScoringStudents(Long examId);

    List<User> getFailedStudents(Long examId, int passMark);

    long countAttemptsByExam(Long examId);

    ExamAttempt gradeAttempt(Long attemptId);

    List<ExamAttempt> getUngradedAttempts(Long examId);
}

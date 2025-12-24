package com.sethukumar.OnlineExaminationSystem.repository;

import com.sethukumar.OnlineExaminationSystem.dto.ExamResultDTO;
import com.sethukumar.OnlineExaminationSystem.models.ExamAttempt;
import com.sethukumar.OnlineExaminationSystem.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExamAttemptRepository extends JpaRepository<ExamAttempt, Long> {

    List<ExamAttempt> findByStudentId(Long studentId);

    long countByExamId(Long examId);

    @Query("""
    SELECT a.student
    FROM ExamAttempt a
    WHERE a.exam.id = :examId
      AND a.totalScore = (
          SELECT MAX(a2.totalScore)
          FROM ExamAttempt a2
          WHERE a2.exam.id = :examId
      )
    """)
    List<Student> findHighestScoringStudents(@Param("examId") Long examId);


    @Query("""
        SELECT a.student
        FROM ExamAttempt a
        WHERE a.exam.id = :examId
        AND a.totalScore < :passMark
    """)
    List<Student> findFailedStudents(
            @Param("examId") Long examId,
            @Param("passMark") int passMark
    );

    @Query("""
    SELECT COALESCE(SUM(aq.score), 0)
    FROM ExamAttemptQuestion aq
    WHERE aq.attempt.id = :attemptId
    """)
    Integer calculateTotalScore(@Param("attemptId") Long attemptId);

    @Query("""
    SELECT new com.sethukumar.OnlineExaminationSystem.dto.ExamResultDTO(
        s.id,
        s.name,
        a.totalScore
    )
    FROM ExamAttempt a
    JOIN a.student s
    WHERE a.exam.id = :examId
""")
    List<ExamResultDTO> findResultsByExam(
            @Param("examId") Long examId
    );


}

package com.sethukumar.OnlineExaminationSystem.repository;

import com.sethukumar.OnlineExaminationSystem.models.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Long> {

    List<Exam> findByTeacherId(Long teacherId);

    @Query("""
        SELECT a.exam
        FROM ExamAttempt a
        GROUP BY a.exam
        HAVING AVG(a.totalScore) > :score
    """)
    List<Exam> findExamsWithAverageScoreGreaterThan(@Param("score") double score);
}

package com.sethukumar.OnlineExaminationSystem.repository;

import com.sethukumar.OnlineExaminationSystem.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByExamId(Long examId);
    @Query("""
    SELECT q
    FROM Question q
    WHERE q.exam.id = (
        SELECT a.exam.id
        FROM ExamAttempt a
        WHERE a.id = :attemptId
    )
    AND q.id NOT IN (
        SELECT aq.question.id
        FROM ExamAttemptQuestion aq
        WHERE aq.attempt.id = :attemptId
    )
""")
    List<Question> findUnansweredQuestions(@Param("attemptId") Long attemptId);


}

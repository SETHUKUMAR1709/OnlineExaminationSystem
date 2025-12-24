package com.sethukumar.OnlineExaminationSystem.repository;

import com.sethukumar.OnlineExaminationSystem.models.ExamAttemptQuestion;
import com.sethukumar.OnlineExaminationSystem.models.ExamAttemptQuestionId;
import com.sethukumar.OnlineExaminationSystem.dto.ScorePerQuestionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExamAttemptQuestionRepository
        extends JpaRepository<ExamAttemptQuestion, ExamAttemptQuestionId> {

    List<ExamAttemptQuestion> findByAnswerIsNull();


    @Query("""
        SELECT aq.question, AVG(aq.score)
        FROM ExamAttemptQuestion aq
        GROUP BY aq.question
        ORDER BY AVG(aq.score) ASC
    """)
    List<Object[]> findMostDifficultQuestions();

    @Query("""
    SELECT new com.sethukumar.OnlineExaminationSystem.dto.ScorePerQuestionDTO(
        q.id,
        q.text,
        q.maxMarks,
        aq.score
    )
    FROM ExamAttemptQuestion aq
    JOIN aq.question q
    WHERE aq.attempt.id = :attemptId
""")
    List<ScorePerQuestionDTO> findScoresByAttempt(
            @Param("attemptId") Long attemptId
    );

}

package com.sethukumar.OnlineExaminationSystem.controllers;

import com.sethukumar.OnlineExaminationSystem.models.ExamAttemptQuestion;
import com.sethukumar.OnlineExaminationSystem.services.interfaces.ExamAttemptQuestionService;
import com.sethukumar.OnlineExaminationSystem.dto.ScorePerQuestionDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/answers")
public class ExamAttemptQuestionController {

    private final ExamAttemptQuestionService service;

    public ExamAttemptQuestionController(ExamAttemptQuestionService service) {
        this.service = service;
    }

    @PostMapping
    public ExamAttemptQuestion submit(@RequestBody ExamAttemptQuestion answer) {
        return service.submitAnswer(answer);
    }

    @PutMapping("/grade")
    public ExamAttemptQuestion gradeAnswer(
            @RequestParam Long attemptId,
            @RequestParam Long questionId,
            @RequestParam Integer score,
            @RequestParam Long gradedById) {
        return service.gradeAnswer(attemptId, questionId, score, gradedById);
    }

    @GetMapping("/scores/{attemptId}")
    public List<ScorePerQuestionDTO> scores(@PathVariable Long attemptId) {
        return service.getScoresByAttempt(attemptId);
    }

    @GetMapping("/difficult")
    public List<Object[]> difficultQuestions() {
        return service.getMostDifficultQuestions();
    }
}

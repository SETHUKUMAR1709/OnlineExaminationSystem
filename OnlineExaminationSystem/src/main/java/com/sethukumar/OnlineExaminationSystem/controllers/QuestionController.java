package com.sethukumar.OnlineExaminationSystem.controllers;

import com.sethukumar.OnlineExaminationSystem.models.Question;
import com.sethukumar.OnlineExaminationSystem.services.interfaces.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService service;

    public QuestionController(QuestionService service) {
        this.service = service;
    }

    @PostMapping
    public Question create(@RequestBody Question question) {
        return service.createQuestion(question);
    }

    @GetMapping("/{id}")
    public Question get(@PathVariable Long id) {
        return service.getQuestion(id);
    }

    // 2️⃣ Questions in an exam
    @GetMapping("/exam/{examId}")
    public List<Question> byExam(@PathVariable Long examId) {
        return service.getQuestionsByExam(examId);
    }

    @PutMapping("/{id}")
    public Question update(@PathVariable Long id, @RequestBody Question question) {
        return service.updateQuestion(id, question);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteQuestion(id);
    }
    @GetMapping("/unanswered/attempt/{attemptId}")
    public List<Question> getUnanswered(@PathVariable Long attemptId) {
        return service.getUnansweredQuestions(attemptId);
    }

}


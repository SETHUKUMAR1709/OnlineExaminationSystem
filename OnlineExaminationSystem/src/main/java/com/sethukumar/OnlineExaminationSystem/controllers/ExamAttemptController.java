package com.sethukumar.OnlineExaminationSystem.controllers;

import com.sethukumar.OnlineExaminationSystem.dto.ExamResultDTO;
import com.sethukumar.OnlineExaminationSystem.models.ExamAttempt;
import com.sethukumar.OnlineExaminationSystem.models.User;
import com.sethukumar.OnlineExaminationSystem.services.interfaces.ExamAttemptService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attempts")
public class ExamAttemptController {

    private final ExamAttemptService service;

    public ExamAttemptController(ExamAttemptService service) {
        this.service = service;
    }

    @PostMapping
    public ExamAttempt create(@RequestBody ExamAttempt attempt) {
        return service.createAttempt(attempt);
    }

    @PostMapping("/start")
    public ExamAttempt startAttempt(
            @RequestParam Long examId,
            @RequestParam Long studentId) {
        return service.startAttempt(examId, studentId);
    }

    @GetMapping("/{id}")
    public ExamAttempt get(@PathVariable Long id) {
        return service.getAttempt(id);
    }

    @GetMapping("/student/{studentId}")
    public List<ExamAttempt> byStudent(@PathVariable Long studentId) {
        return service.getAttemptsByStudent(studentId);
    }

    @GetMapping("/count/exam/{examId}")
    public long countByExam(@PathVariable Long examId) {
        return service.countAttemptsByExam(examId);
    }

    @GetMapping("/topper/exam/{examId}")
    public List<User> highestScorers(@PathVariable Long examId) {
        return service.getHighestScoringStudents(examId);
    }

    @GetMapping("/failed/exam/{examId}/{passMark}")
    public List<User> failedStudents(
            @PathVariable Long examId,
            @PathVariable int passMark) {
        return service.getFailedStudents(examId, passMark);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteAttempt(id);
    }

    @GetMapping("/results/exam/{examId}")
    public List<ExamResultDTO> getExamResults(@PathVariable Long examId) {
        return service.getResultsByExam(examId);
    }

    @PutMapping("/{id}/grade")
    public ExamAttempt gradeAttempt(@PathVariable Long id) {
        return service.gradeAttempt(id);
    }

    @GetMapping("/ungraded/exam/{examId}")
    public List<ExamAttempt> ungradedAttempts(@PathVariable Long examId) {
        return service.getUngradedAttempts(examId);
    }
}

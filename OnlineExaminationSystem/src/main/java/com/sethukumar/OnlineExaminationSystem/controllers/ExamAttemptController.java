package com.sethukumar.OnlineExaminationSystem.controllers;

import com.sethukumar.OnlineExaminationSystem.dto.ExamResultDTO;
import com.sethukumar.OnlineExaminationSystem.models.ExamAttempt;
import com.sethukumar.OnlineExaminationSystem.models.Student;
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
    public List<Student> highestScorers(@PathVariable Long examId) {
        return service.getHighestScoringStudents(examId);
    }

    @GetMapping("/failed/exam/{examId}/{passMark}")
    public List<Student> failedStudents(
            @PathVariable Long examId,
            @PathVariable int passMark) {
        return service.getFailedStudents(examId, passMark);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteAttempt(id);
    }

    @GetMapping("/results/exam/{examId}")
    public List<ExamResultDTO> getExamResults(
            @PathVariable Long examId
    ) {
        return service.getResultsByExam(examId);
    }

}


package com.sethukumar.OnlineExaminationSystem.controllers;

import com.sethukumar.OnlineExaminationSystem.models.Exam;
import com.sethukumar.OnlineExaminationSystem.services.interfaces.ExamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    private final ExamService service;

    public ExamController(ExamService service) {
        this.service = service;
    }

    @PostMapping
    public Exam create(@RequestBody Exam exam) {
        return service.createExam(exam);
    }

    @GetMapping("/{id}")
    public Exam get(@PathVariable Long id) {
        return service.getExam(id);
    }

    @GetMapping
    public List<Exam> getAll() {
        return service.getAllExams();
    }

    @PutMapping("/{id}")
    public Exam update(@PathVariable Long id, @RequestBody Exam exam) {
        return service.updateExam(id, exam);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteExam(id);
    }

    // 1️⃣ Exams by teacher
    @GetMapping("/teacher/{teacherId}")
    public List<Exam> byTeacher(@PathVariable Long teacherId) {
        return service.getExamsByTeacher(teacherId);
    }

    // 8️⃣ Exams with avg score > X
    @GetMapping("/average/{score}")
    public List<Exam> examsAboveAverage(@PathVariable double score) {
        return service.getExamsWithAverageScoreGreaterThan(score);
    }
}


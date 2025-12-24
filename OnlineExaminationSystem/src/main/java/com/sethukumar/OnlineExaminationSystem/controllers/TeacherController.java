package com.sethukumar.OnlineExaminationSystem.controllers;


import com.sethukumar.OnlineExaminationSystem.models.Teacher;
import com.sethukumar.OnlineExaminationSystem.services.interfaces.TeacherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService service;

    public TeacherController(TeacherService service) {
        this.service = service;
    }

    @PostMapping
    public Teacher create(@RequestBody Teacher teacher) {
        return service.createTeacher(teacher);
    }

    @GetMapping("/{id}")
    public Teacher get(@PathVariable Long id) {
        return service.getTeacher(id);
    }

    @GetMapping
    public List<Teacher> getAll() {
        return service.getAllTeachers();
    }

    @PutMapping("/{id}")
    public Teacher update(@PathVariable Long id, @RequestBody Teacher teacher) {
        return service.updateTeacher(id, teacher);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteTeacher(id);
    }
}


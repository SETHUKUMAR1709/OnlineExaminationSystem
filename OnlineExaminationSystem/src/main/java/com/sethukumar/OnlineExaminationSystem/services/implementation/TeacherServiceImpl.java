package com.sethukumar.OnlineExaminationSystem.services.implementation;

import com.sethukumar.OnlineExaminationSystem.models.Teacher;
import com.sethukumar.OnlineExaminationSystem.services.interfaces.TeacherService;
import com.sethukumar.OnlineExaminationSystem.repository.TeacherRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository repo;

    public TeacherServiceImpl(TeacherRepository repo) {
        this.repo = repo;
    }

    public Teacher createTeacher(Teacher teacher) {
        return repo.save(teacher);
    }

    public Teacher getTeacher(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
    }

    public List<Teacher> getAllTeachers() {
        return repo.findAll();
    }

    public Teacher updateTeacher(Long id, Teacher teacher) {
        Teacher existing = getTeacher(id);
        existing.setName(teacher.getName());
        existing.setEmail(teacher.getEmail());
        return repo.save(existing);
    }

    public void deleteTeacher(Long id) {
        repo.deleteById(id);
    }
}


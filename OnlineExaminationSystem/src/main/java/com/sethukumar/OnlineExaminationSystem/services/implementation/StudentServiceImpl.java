package com.sethukumar.OnlineExaminationSystem.services.implementation;

import com.sethukumar.OnlineExaminationSystem.models.Student;
import com.sethukumar.OnlineExaminationSystem.repository.StudentRepository;
import com.sethukumar.OnlineExaminationSystem.services.interfaces.StudentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repo;

    public StudentServiceImpl(StudentRepository repo) {
        this.repo = repo;
    }

    public Student createStudent(Student student) {
        return repo.save(student);
    }

    public Student getStudent(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    public Student updateStudent(Long id, Student student) {
        Student existing = getStudent(id);
        existing.setName(student.getName());
        existing.setEmail(student.getEmail());
        return repo.save(existing);
    }

    public void deleteStudent(Long id) {
        repo.deleteById(id);
    }
}


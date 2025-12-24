package com.sethukumar.OnlineExaminationSystem.repository;

import com.sethukumar.OnlineExaminationSystem.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {}

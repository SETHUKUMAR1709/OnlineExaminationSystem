package com.sethukumar.OnlineExaminationSystem.services.interfaces;

import com.sethukumar.OnlineExaminationSystem.models.Teacher;

import java.util.List;

public interface TeacherService {
    Teacher createTeacher(Teacher teacher);
    Teacher getTeacher(Long id);
    List<Teacher> getAllTeachers();
    Teacher updateTeacher(Long id, Teacher teacher);
    void deleteTeacher(Long id);
}

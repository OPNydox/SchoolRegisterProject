package com.example.school.services.interfaces;

import java.util.List;

import com.example.school.database.entities.Teacher;
import com.example.school.viewModels.TeacherViewModel;

public interface ITeacherCourseService {
    Iterable<Teacher> getTeachersForCourse(Long courseId);
    
    Iterable<TeacherViewModel> getTeachersVMForCourse(Long courseId);
}

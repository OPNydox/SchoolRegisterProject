package com.example.school.services.interfaces;

import com.example.school.viewModels.StudentViewModel;

public interface IStudentCourseService {
    Iterable<StudentViewModel> getStudentsForCourse(String courseId);
}

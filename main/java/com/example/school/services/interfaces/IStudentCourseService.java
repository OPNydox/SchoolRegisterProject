package com.example.school.services.interfaces;

import com.example.school.utilities.ServiceReturnResult;

public interface IStudentCourseService {
    ServiceReturnResult getStudentsForCourse(String courseId);
}

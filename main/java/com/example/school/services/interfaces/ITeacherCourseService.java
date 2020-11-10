package com.example.school.services.interfaces;

import com.example.school.utilities.ServiceReturnResult;

public interface ITeacherCourseService {
    ServiceReturnResult getTeachersForCourse(String courseId);    
}

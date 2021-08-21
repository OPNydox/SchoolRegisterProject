package com.example.school.viewModels.ViewModelPairs;

import com.example.school.viewModels.CourseViewModel;
import com.example.school.viewModels.TeacherViewModel;

public class TeacherCoursePair {
    private String teacherEmail;

    private Long courseId;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }
    
    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }
}

package com.example.school.utilities;

import com.example.school.viewModels.CourseViewModel;
import com.example.school.viewModels.StudentViewModel;

public class StudentCoursePair {
    private StudentViewModel student;

    private CourseViewModel course;

    public StudentCoursePair() {
        this.student = new StudentViewModel();
        this.course = new CourseViewModel();
    }

    public void setStudentMail (String studentEmail) {
        this.student.setEmail(studentEmail);
    }

    public void setClassId(String courseId) {
        this.course.setId(courseId);
    }

    public String getStundentEmail() {
        return this.student.getEmail();
    }

    public String getCourseId() {
        return this.course.getId();
    } 
}

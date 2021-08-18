package com.example.school.utilities.checkers;

import com.example.school.database.entities.Course;
import com.example.school.database.entities.Student;

public class StudentChecker {
    Student student;

    public StudentChecker(Student student) {
        this.student = student;
    }

    public boolean checkIfStudentEnrolledInClass(Long classId) {
        for (Course course : student.getCourses()) {
            if (course.getCourseId() == classId) {
                return true;
            }
        }

        return false;
    }
}

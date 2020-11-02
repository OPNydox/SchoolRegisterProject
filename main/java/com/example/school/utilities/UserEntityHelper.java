package com.example.school.utilities;

import java.util.ArrayList;
import java.util.List;

import com.example.school.database.entities.Course;
import com.example.school.database.entities.Student;
import com.example.school.database.entities.Teacher;
import com.example.school.database.entities.User;
import com.example.school.viewModels.StudentViewModel;
import com.example.school.viewModels.ViewModel;

public class UserEntityHelper {
    public static List<Course> getCoursesFromUser (User user) {
        List<Course> result = new ArrayList<>();
        Student student;
        Teacher teacher;
        
        if (isUserStudent(user)) {
            student = (Student) user;
            result = getCoursesFromStudent(student);
            return result;
        }

        teacher = (Teacher) user;
        result = getCoursesFromTeacher(teacher);
        return result;

    }

    public static boolean isUserStudent(User user) {
        if (user.isStudent()) {
            return true;
        } 

        return false;
    }

    public static boolean isUserStudent(ViewModel viewModel) {
        if (viewModel instanceof StudentViewModel) {
            return true;
        }
        return false;
    }

    public static List<Course> getCoursesFromStudent(Student student) {
        List<Course> resultList = new ArrayList<>();
        resultList = student.getCourses();
        return resultList;
    }

    public static List<Course> getCoursesFromTeacher(Teacher teacher) { 
        List<Course> resultList = new ArrayList<>();
        resultList = teacher.getCourses();
        return resultList; 
    }
}
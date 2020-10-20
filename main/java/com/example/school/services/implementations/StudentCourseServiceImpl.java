package com.example.school.services.implementations;

import java.util.ArrayList;

import com.example.school.database.entities.Course;
import com.example.school.database.entities.Student;
import com.example.school.services.interfaces.ICourseService;
import com.example.school.services.interfaces.IStudentCourseService;
import com.example.school.services.interfaces.IStudentService;
import com.example.school.utilities.NumberHandler;
import com.example.school.utilities.ServiceReturnResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import antlr.collections.List;

@Service
public class StudentCourseServiceImpl implements IStudentCourseService {

    @Autowired
    private IStudentService studentService;
    
    @Autowired
    private ICourseService courseService;

    @Override
    public ServiceReturnResult getStudentsForCourse(String courseId) {
        ArrayList<Student> enrolledStudents;
        Course course;
        ServiceReturnResult courseFindResult;
        ServiceReturnResult listOfstudentsResult = new ServiceReturnResult();

        courseFindResult = courseService.getCourseById(courseId);

        if (!courseFindResult.isSuccessful()) {
            return courseFindResult;
        }

        course = (Course) courseFindResult.getReturnResultObject();

        listOfstudentsResult.setReturnResultObject(course.getStudents());

        return listOfstudentsResult;
    }
    
}
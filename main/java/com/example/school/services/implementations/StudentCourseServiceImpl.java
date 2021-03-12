package com.example.school.services.implementations;

import java.util.ArrayList;

import com.example.school.database.entities.Course;
import com.example.school.database.entities.Student;
import com.example.school.services.interfaces.ICourseService;
import com.example.school.services.interfaces.IStudentCourseService;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.utilities.mappers.StudentMapper;
import com.example.school.viewModels.StudentViewModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentCourseServiceImpl implements IStudentCourseService {
    
    @Autowired
    private ICourseService courseService;

    @Override
    public Iterable<StudentViewModel> getStudentsForCourse(String courseId) {
        ArrayList<Student> enrolledStudentEntities = new ArrayList<>();
        Iterable<StudentViewModel> mappedStudents;
        Course course;
        ServiceReturnResult<Course> courseFindResult;

        courseFindResult = courseService.getCourseById(courseId);

        course = courseFindResult.getReturnResultObject();

        enrolledStudentEntities.addAll(course.getStudents());

        mappedStudents = StudentMapper.mapStudentsToViewModels(enrolledStudentEntities);

        return mappedStudents;
    }
    
}
package com.example.school.services.implementations;

import java.util.ArrayList;
import java.util.List;

import com.example.school.database.entities.Course;
import com.example.school.database.entities.Teacher;
import com.example.school.services.interfaces.ICourseService;
import com.example.school.services.interfaces.ITeacherCourseService;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.utilities.mappers.TeacherMapper;
import com.example.school.viewModels.TeacherViewModel;

import org.springframework.beans.factory.annotation.Autowired;

public class TeacherCourseServiceImpl implements ITeacherCourseService {

    @Autowired
    private ICourseService courseService;

    @Override
    public ServiceReturnResult getTeachersForCourse(String courseId) {
        ServiceReturnResult getTeachReturnResult = new ServiceReturnResult();
        List<Teacher> teachersFond = new ArrayList<>();
        Iterable<TeacherViewModel> mappedTeachers = new ArrayList<>();
        Course course;
        ServiceReturnResult courseFindResult = new ServiceReturnResult();

        courseFindResult = courseService.getCourseById(courseId);

        if (!courseFindResult.isSuccessful()) {
            return courseFindResult;
        }

        course = (Course) courseFindResult.getReturnResultObject();
        teachersFond.addAll(course.getTeachers());
        mappedTeachers = TeacherMapper.mapTeacherEntityToViewModel(teachersFond);


        return getTeachReturnResult;
    }
    
}

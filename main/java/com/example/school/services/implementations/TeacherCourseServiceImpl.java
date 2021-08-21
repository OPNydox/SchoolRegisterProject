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
import org.springframework.stereotype.Service;

@Service
public class TeacherCourseServiceImpl implements ITeacherCourseService {

    @Autowired
    private ICourseService courseService;

    @Override
    public Iterable<TeacherViewModel> getTeachersVMForCourse(Long courseId) {
        Iterable<Teacher> teacherEnteties;
        Iterable<TeacherViewModel> teacherViewModels;

        teacherEnteties = getTeachersForCourse(courseId);
        teacherViewModels = TeacherMapper.mapTeacherEntityToViewModel(teacherEnteties);
        return teacherViewModels;
    }
    
    @Override
    public Iterable<Teacher> getTeachersForCourse(Long courseId) {
        List<Teacher> teachersFond = new ArrayList<>();
        Course course;
        ServiceReturnResult<Course> courseFindResult = new ServiceReturnResult<>();

        courseFindResult = courseService.getCourseById(courseId);

        course = courseFindResult.getReturnResultObject();
        teachersFond.addAll(course.getTeachers());

        return teachersFond;
    }
}

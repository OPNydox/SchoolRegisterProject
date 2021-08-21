package com.example.school.utilities.mappers;

import java.util.HashSet;
import java.util.Set;

import com.example.school.database.entities.Course;
import com.example.school.viewModels.CourseViewModel;

public class CourseMapper {
    public static CourseViewModel mapEntityToCourseViewModel(Course course) {
        CourseViewModel resultViewModel = new CourseViewModel();

        resultViewModel.setId(course.getCourseId());
        resultViewModel.setHonorarium(Integer.toString(course.getHonorarium()));
        resultViewModel.setName(course.getCourseName());
        resultViewModel.setSubject(course.getSubject());
        
        return resultViewModel;
    }

    public static Set<CourseViewModel> mapEntityToCourseViewModel(Iterable<Course> list) {
        Set<CourseViewModel> resultSet = new HashSet<>();

        for (Course course : list) {
            resultSet.add(mapEntityToCourseViewModel(course));
        }

        return resultSet;
    }
}
package com.example.school.utilities.mappers;

import java.util.ArrayList;
import java.util.List;

import com.example.school.database.entities.Course;
import com.example.school.viewModels.CourseViewModel;

public class CourseMapper {
    public static CourseViewModel mapEtityoToCourseViewModel(Course course) {
        CourseViewModel resultViewModel = new CourseViewModel();

        resultViewModel.setId(Long.toString(course.getClassId()));
        resultViewModel.setHonorarium(Integer.toString(course.getHonorarium()));
        resultViewModel.setName(course.getCourseName());
        resultViewModel.setSubject(course.getSubject());
        
        return resultViewModel;
    }

    public static List<CourseViewModel> mapEtityoToCourseViewModel (Iterable<Course> list) {
        List<CourseViewModel> resultList = new ArrayList<>();

        for (Course course : list) {
            resultList.add(mapEtityoToCourseViewModel(course));
        }

        return resultList;
    }
}
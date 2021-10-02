package com.example.school.services.implementations;

import com.example.school.viewModels.StudentProfileViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    @Autowired
    private CourseServiceImpl courseService;

    @Autowired
    private GradeServiceImpl gradeService;

    public StudentProfileViewModel getProfileView(String personEmail) {
        StudentProfileViewModel viewModel = new StudentProfileViewModel();
        viewModel.setCourses(courseService.getAllCoursesForPerson(personEmail));


        return viewModel;
    }
}

package com.example.school.services.implementations;

import com.example.school.viewModels.ProfileViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    @Autowired
    private CourseServiceImpl courseService;

    @Autowired
    private GradeServiceImpl gradeService;

    public ProfileViewModel getProfileView(String personEmail) {
        ProfileViewModel viewModel = new ProfileViewModel();
        viewModel.setCourses(courseService.getAllCoursesForPerson(personEmail));


        return viewModel;
    }
}

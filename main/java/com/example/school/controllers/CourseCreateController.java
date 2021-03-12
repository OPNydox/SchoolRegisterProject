package com.example.school.controllers;

import com.example.school.services.interfaces.ICourseService;
import com.example.school.servicesImplementations.CourseService;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.viewModels.CourseViewModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CourseCreateController {
    @Autowired
    private ICourseService courseService;

    @GetMapping(value = "/courseCreate")
    public String getCourseCreate(Model model) {
        if (!model.containsAttribute("courseFormObject")) {
            CourseViewModel courseViewModel = new CourseViewModel();
            model.addAttribute("courseFormObject", courseViewModel);
        } 

        return "courseCreate";
    }

    @PostMapping(value = "/courseCreate")
    public String createCourse(@ModelAttribute CourseViewModel courseModel ,Model model) {
        ServiceReturnResult createResult;

        createResult = courseService.addCourse(courseModel);

        if (createResult.hasErrors()) {
            model.addAttribute("error", createResult.getErrorMessages());
            model.addAttribute("courseFormObject", courseModel);

            return "courseCreate";
        }

        return "redirect:/courses";
    }
}

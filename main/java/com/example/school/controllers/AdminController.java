package com.example.school.controllers;

import com.example.school.services.implementations.UserService;
import com.example.school.services.interfaces.ITeacherService;
import com.example.school.utilities.ControllerUtils;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.viewModels.Interfaces.UserViewModel;
import com.example.school.viewModels.TeacherRegistrationViewModel;
import com.example.school.viewModels.TeacherViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private UserService userService;

    @GetMapping("/create/teacher")
    public String getCreateTeacherPage(Model model) {
        if (!model.containsAttribute("teacherFormModel")) {
            model.addAttribute("teacherFormModel", new TeacherRegistrationViewModel());
        }

        return "teacherCreate";
    }

    @GetMapping(value = "/profile")
    public String getAdminProfilePage(Model model) {
        UserViewModel userViewModel = ControllerUtils.getUserPrincipal().getUser();
        Set<UserViewModel> teachers = teacherService.getAllTeachers();

        model.addAttribute("teachers", teachers);

        return "adminProfile";

    }

    @PostMapping("/create/teacher")
    public String createTeacher(@ModelAttribute TeacherRegistrationViewModel teacherViewModel, Model model) {
        ServiceReturnResult<TeacherViewModel> teacherCreateResult = new ServiceReturnResult<>();

        teacherCreateResult = teacherService.addTeacher(teacherViewModel);

        if (teacherCreateResult.hasErrors()) {
            model.addAttribute("teacherFormModel", teacherCreateResult);
            model.addAttribute("errors", teacherCreateResult.getErrorMessages());
            return "teacherCreate";
        }

        return "redirect:/profile";
    }
}

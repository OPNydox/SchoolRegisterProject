package com.example.school.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.school.services.interfaces.IStudentService;
import com.example.school.viewModels.StudentRegistrationViewModel;

@Controller
public class RegisterController {

	@Autowired
	private IStudentService studentService;


	@GetMapping(value="/register")
	public String registerGet(Model model) {
		model.addAttribute("registrationForm", new StudentRegistrationViewModel());
		return "register";
	}
	
	@PostMapping(value="/register")
	public String registerPost(@RequestParam(value = "error", required = false) String error,
							   @ModelAttribute StudentRegistrationViewModel studentRegistrationViewModel, Model model) {
		studentService.createStudent(studentRegistrationViewModel);
		return "login";
	}
}

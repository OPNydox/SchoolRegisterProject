package com.example.school.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.school.database.entities.AuthGroup;
import com.example.school.services.interfaces.IAuthGroupService;
import com.example.school.services.interfaces.IStudentService;
import com.example.school.utilities.ControllerHelper;
import com.example.school.utilities.enums.UserRole;
import com.example.school.viewModels.AuthViewModel;
import com.example.school.viewModels.RegistrationViewModel;
import com.example.school.viewModels.StudentViewModel;

@Controller
public class RegisterController {

	@Autowired
	private IStudentService studentService;


	@GetMapping(value="/register")
	public String registerGet(Model model) {
		model.addAttribute("registrationForm", new RegistrationViewModel());
		return "register";
	}
	
	@PostMapping(value="/register")
	public String registerPost(@RequestParam(value = "error", required = false) String error,
			@ModelAttribute RegistrationViewModel registrationViewModel, Model model) {

		studentService.createStudent(registrationViewModel);
		
		return "login";
	}
}

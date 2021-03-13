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

	@Autowired
	private IAuthGroupService authService;
	
	private RegistrationViewModel registrationForm;
	
	private StudentViewModel studentModel;

	private AuthGroup authGroup;

	@GetMapping(value="/register")
	public String registerGet(Model model) {
		model.addAttribute("registrationForm", new RegistrationViewModel());
		return "register";
	}
	
	@PostMapping(value="/register")
	public String registerPost(@RequestParam(value = "error", required = false) String error,
			@ModelAttribute RegistrationViewModel view, Model model) {
		this.registrationForm = view;
		
		error = GetErrorMessage();
		
		if (!error.isEmpty()) {
			model.addAttribute("error", error);
			return "register";
		}
		
		generateStudentModel();
		studentService.createStudent(studentModel);
		
		return "login";
		
	}
	
	private String GetErrorMessage() {
		String error;
		if (!checkIfMapIsLoaded()) {
			error = "There has been an internal server error";
			return error;
		}
		
		if (!checkifFieldsFull()) {
			error = "Please fill out all the fields";
			return error;
		}
		
		if (!checkIfTheUserEnteredPasswordsMatch()) {
			error = "Passwords do not match";
			return error;
		}
		
		return new String();
	}
	
	private boolean checkIfMapIsLoaded() {
		if (this.registrationForm == null) {
			return false;
		}
		
		return true;
	}
	
	private boolean checkifFieldsFull() {
		if (this.registrationForm.getName().isEmpty() ||
				this.registrationForm.getEmail().isEmpty() ||
				this.registrationForm.getPassword().isEmpty() || 
				this.registrationForm.getPasswordRepeat().isEmpty()) {
			return false;
		}
		
		return true;
	}
	
	private boolean checkIfTheUserEnteredPasswordsMatch() {
		String password = this.registrationForm.getPassword();
		String repeadPassword = this.registrationForm.getPasswordRepeat();
		
		return password.equals(repeadPassword);
	}
	
	private void generateStudentModel() {
		StudentViewModel resultModel = new StudentViewModel();
		resultModel.setEmail(registrationForm.getEmail());
		resultModel.setName(registrationForm.getName());
		resultModel.setPassword(registrationForm.getPassword());
		
		this.studentModel = resultModel;
	}
	
	
	
}

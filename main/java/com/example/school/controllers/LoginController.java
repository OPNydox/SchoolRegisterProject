package com.example.school.controllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.school.viewModels.LoginViewModel;

@Controller
public class LoginController {
	
	private LoginViewModel loginDetails;
	
	private String errorMessage;
	
	private String logout;

	@GetMapping(value="/login")
	public String login(Model model) {
		model.addAttribute("loginForm", new LoginViewModel());
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPage(@RequestParam(value = "error", required = false) String error, 
                            @RequestParam(value = "logout", required = false) String logout,
                            @ModelAttribute LoginViewModel loginForm,
                            Model model) {
		String displayError;
		
		this.loginDetails = loginForm;
        this.errorMessage = error;
        
        displayError = getErrorString();
        
        model.addAttribute("errorMessge", displayError);
        return "redirect:profile";
    }
	
	private String getErrorString() {
		if (checkIfFormEmpty()) {
			return "All fields are required";
		}
		
		if(this.errorMessage != null) {
            return "Username or Password is incorrect !!";
        }
		
        if(this.logout != null) {
            return "You have been logged out !";
        }
        
        return "";
	}
	
	private boolean checkIfFormEmpty() {
		String email = this.loginDetails.getEmail();
		String password = this.loginDetails.getPassword();
		
		if (email == null || password == null ||
				email.isEmpty() || password.isEmpty()) {
			return true;
		}
		
		return false;
		
	}
}

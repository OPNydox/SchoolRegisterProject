package com.example.school.viewModels.decorators.dataValidators;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class EmailValidator {
	
	private String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
	
	public List<String> validateEmail(String email) {
		List<String> result = new ArrayList<String>();
		
		if (email.matches(this.regex)) {
			return result;
		}
		
		result.add("Email is not in a valid format.");
		
		return result;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}
	
}

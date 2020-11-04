package com.example.school.viewModels;

import com.example.school.viewModels.Interfaces.ViewModel;

public class LoginViewModel  extends ViewModel {
	private String email;
	
	private String password;
	
	public LoginViewModel() { }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}

package com.example.school.viewModels;

import com.example.school.utilities.Constants;
import com.example.school.utilities.enums.UserRole;
import com.example.school.viewModels.Interfaces.IUserViewModel;
import com.example.school.viewModels.Interfaces.ViewModel;

public class RegistrationViewModel extends ViewModel implements IUserViewModel{
	String name;
	
	String email;
	
	String password;
	
	String passwordRepeat;

	UserRole role = UserRole.STUDENT;
	
	String error;

	public RegistrationViewModel(String name, String email, String password, String passwordRepeat) {
		super();
		this.setName(name);
		this.setEmail(email);
		this.setPassword(password);
		this.setPasswordRepeat(passwordRepeat);
	}

	public RegistrationViewModel() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public String getPasswordRepeat() {
		return passwordRepeat;
	}

	public void setPasswordRepeat(String passwordRepeat) {
		this.passwordRepeat = passwordRepeat;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	public UserRole getRole() {
		return role;
	}
}

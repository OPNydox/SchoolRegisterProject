package com.example.school.viewModels;

import com.example.school.utilities.enums.UserRole;
import com.example.school.viewModels.Interfaces.UserViewModel;

public class StudentRegistrationViewModel extends UserViewModel {
	String password;
	
	String passwordRepeat;
	
	String error;

	{
		this.setUserRole(UserRole.STUDENT);
	}

	public StudentRegistrationViewModel(String name, String email, String password, String passwordRepeat) {
		super(email, name);
		this.setPassword(password);
		this.setPasswordRepeat(passwordRepeat);
	}

	public StudentRegistrationViewModel() {}

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
}

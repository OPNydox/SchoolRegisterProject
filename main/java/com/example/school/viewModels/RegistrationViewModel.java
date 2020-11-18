package com.example.school.viewModels;

import com.example.school.utilities.enums.UserRole;
import com.example.school.viewModels.Interfaces.UserViewModel;

public class RegistrationViewModel extends UserViewModel {
	String password;
	
	String passwordRepeat;
	
	String error;

	public RegistrationViewModel(String name, String email, String password, String passwordRepeat) {
		super(email, name);
		this.setUserRole(UserRole.STUDENT);
		this.setPassword(password);
		this.setPasswordRepeat(passwordRepeat);
	}

	public RegistrationViewModel() {}

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

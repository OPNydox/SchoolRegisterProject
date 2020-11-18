package com.example.school.viewModels;

import com.example.school.utilities.enums.UserRole;
import com.example.school.viewModels.Interfaces.UserViewModel;

public class TeacherViewModel extends UserViewModel {
	private String password;

	private String passwordRepeat;
	
	private String salary;

	private UserRole role = UserRole.TEACHER;
	
	public TeacherViewModel() { }
	
	public TeacherViewModel(String name, String email, String password, String salary) {
		super(email, name);
		this.setUserRole(UserRole.TEACHER);
		this.setPassword(password);
		this.setSalary(salary);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getPasswordRepeat() {
		return passwordRepeat;
	}

	public void setPasswordRepeat(String passwordRepeat) {
		this.passwordRepeat = passwordRepeat;
	}

	public UserRole getRole() {
		return role;
	}
}

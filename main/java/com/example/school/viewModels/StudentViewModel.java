package com.example.school.viewModels;

import com.example.school.utilities.enums.UserRole;
import com.example.school.viewModels.Interfaces.UserViewModel;
import com.example.school.viewModels.Interfaces.ViewModel;

public class StudentViewModel extends UserViewModel {
	private String id;

	private String name;

	private String password;

	private String email;

	private UserRole userRole;

	public StudentViewModel() {
		this.userRole = UserRole.STUDENT;
	}

	public StudentViewModel(String name, String password) {
		super();
		this.name = name;
		this.password = password;
		this.userRole = UserRole.STUDENT;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}

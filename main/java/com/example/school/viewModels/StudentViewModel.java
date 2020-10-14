package com.example.school.viewModels;

public class StudentViewModel extends ViewModel {
	private String name;
	
	private String password;
	
	private String email;
	
	public StudentViewModel() {}

	public StudentViewModel(String name, String password) {
		super();
		this.name = name;
		this.password = password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}

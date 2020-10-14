package com.example.school.database.entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.Transient;

import com.example.school.utilities.interfaces.INullable;

@MappedSuperclass
public class User implements INullable{
	private String name;
	
	@Column(unique = true)
	private String email;
	
	@Column(length = 60)
	private String password;
	
	private boolean isStudent;
	
	@Transient
	private boolean isEmpty;
	
	public User() { }

	public User(String name, String email, String password, boolean isStudent) {
		this.setName(name);
		this.setEmail(email);
		this.setPassword(password);
		this.setStudent(isStudent);
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

	public boolean isStudent() {
		return isStudent;
	}

	public void setStudent(boolean isStudent) {
		this.isStudent = isStudent;
	}

	@Override
	public boolean isNull() {
		if (this.isEmpty ||this.getName() == null || this.getEmail() == null) {
			return true;
		}
		return false;
	}

	@Override
	public void setEmpty() {
		this.isEmpty = true;
	}

}

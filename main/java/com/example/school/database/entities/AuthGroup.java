package com.example.school.database.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AUTH_USER_GROUP")
public class AuthGroup {
	@Id
	@Column(name = "AUTH_USER_GROUP_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "AUTH_GROUP")
	private String authGroup;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAuthGroup() {
		return authGroup;
	}

	public void setAuthGroup(String authGroup) {
		this.authGroup = authGroup;
	}
}

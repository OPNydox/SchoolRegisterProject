package com.example.school.exceptions;

public class UserNotFoundInDatabase extends RuntimeException {
	public UserNotFoundInDatabase(String text) {
		super(text);
	}
}

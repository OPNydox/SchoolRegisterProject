package com.example.school.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.school.exceptions.ValueException;
import com.example.school.exceptions.ValueNotFoundException;
import com.example.school.utilities.interfaces.INullable;

public class Verificator {
	private static final String EMAIL_REGEX = "@.*?\\.";
	private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
	
	public static boolean verifyEmail(String email) {
		if (email == null) {
			return false;
		}

		Matcher matcher = EMAIL_PATTERN.matcher(email);
		return matcher.matches();
	}
	
	public static void isEmpty(Object checkedObject, String errMessage) throws ValueException {
		if (checkedObject == null) {
			throw new ValueNotFoundException(errMessage);
		}
		return;
	}
	
	public static boolean isEmpty(INullable objectToCheck) {
		if (objectToCheck == null || objectToCheck.isNull()) {
			return true;
		}
		
		return false;
	}
}

package com.example.school.viewModels.decorators.dataValidators;

import java.util.ArrayList;
import java.util.List;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class StringValidator {
	
	public List<String> validateString(String value,@Nullable String fieldName, @Nullable Integer maxLength ) {
		List<String> result = new ArrayList<>();
		
		if (fieldName == null) {
			fieldName = "String";
		}
		
		result.addAll(checkEmpty(value, fieldName));
		
		if (!result.isEmpty() || maxLength == null) {
			return result;
		}
		
		if(value.length() > maxLength) {
			result.add(fieldName + " is too long");
		}
		
		return result;
	}
	
	private List<String> checkEmpty(String value, String fieldName) {
		List<String> result = new ArrayList<String>();
		if (value == null) {
			result.add(fieldName + " object is empty.");
		}
		
		if (value.isEmpty()) {
			result.add(fieldName + " is empty");
		}
		
		return result;
	}
}

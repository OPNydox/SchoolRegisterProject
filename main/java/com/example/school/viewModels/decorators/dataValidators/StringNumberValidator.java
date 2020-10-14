package com.example.school.viewModels.decorators.dataValidators;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.math.NumberUtils;

import org.springframework.stereotype.Component;

@Component
public class StringNumberValidator {
	public List<String> validateNumber(String number) {
		List<String> result = new ArrayList<>();
		
		if (number == null || number.isEmpty()) {
			result.add("Number is empty");
			return result;
		}
		
		if (!NumberUtils.isDigits(number)) {
			result.add("This is not a number" + number);
		}
		
		return result;
	}
	
}

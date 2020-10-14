package com.example.school.utilities;

import java.util.HashMap;
import java.util.Map;

import org.springframework.ui.Model;

public class ControllerHelper {
	public static ReturnResult returnError(String message) {
		ReturnResult result = new ReturnResult();
		result.setSuccesful(false);
		result.setMessage(message);
		return result;
	}
	
	public static Map<String, Object> convertModelToMap(Model model) {
		Map<String, Object> resultingMap = new HashMap<>();
		resultingMap = model.asMap();
		return resultingMap;
	}
}

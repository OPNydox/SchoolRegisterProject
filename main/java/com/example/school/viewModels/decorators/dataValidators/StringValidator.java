package com.example.school.viewModels.decorators.dataValidators;

import java.util.ArrayList;
import java.util.List;

import com.example.school.utilities.ServiceReturnResult;

import org.hibernate.service.spi.ServiceException;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class StringValidator {

	private String rolePrefix = "Role_";

	public List<String> validateRoleString(String role) {
		List<String> errors = new ArrayList<>();

		errors.addAll(validateString(role, "role", 50));

		if (!errors.isEmpty()) {
			return errors;
		}

		if (role.startsWith(this.rolePrefix)) {
			errors.add("Role does not match current role prefix");
		}

		return errors;

	}
	
	public List<String> validateString(String value,@Nullable String fieldName, @Nullable Integer maxLength ) {
		List<String> result = new ArrayList<>();
		
		if (fieldName == null) {
			fieldName = "String";
		}
		
		result.addAll(checkEmpty(value, fieldName).getErrorMessages());
		
		if (!result.isEmpty() || maxLength == null) {
			return result;
		}
		
		if(value.length() > maxLength) {
			result.add(fieldName + " is too long");
		}
		
		return result;
	}
	
	private ServiceReturnResult checkEmpty(String value, String fieldName) {
		ServiceReturnResult result = new ServiceReturnResult();
		if (value == null || value.isEmpty()) {
			result.addErrorMsg(fieldName + " is empty.");
		}
		
		return result;
	}

	public String getRolePrefix() {
		return rolePrefix;
	}

	public void setRolePrefix(String rolePrefix) {
		this.rolePrefix = rolePrefix;
	}
}

package com.example.school.viewModels.decorators;

import com.example.school.viewModels.decorators.dataValidators.EmailValidator;
import com.example.school.viewModels.decorators.dataValidators.IdValidator;
import com.example.school.viewModels.decorators.dataValidators.StringNumberValidator;
import com.example.school.viewModels.decorators.dataValidators.StringValidator;

public abstract class VMValidatorBase implements VMValidator{

	protected EmailValidator emailValidator;
	
	protected StringValidator stringValidator;
	
	protected StringNumberValidator numberValidator;
	
	protected IdValidator idValidator;
	
	public VMValidatorBase () {
		emailValidator = new EmailValidator();
		stringValidator = new StringValidator();
		numberValidator = new StringNumberValidator();
		idValidator = new IdValidator();
	}
	
	public boolean changeRegex(String regex) {
		if (regex == null || regex.isEmpty()) {
			return false;
		}
		
		this.emailValidator.setRegex(regex);
		
		return true;
	}
}

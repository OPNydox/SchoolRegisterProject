package com.example.school.viewModels.decorators;

import java.util.ArrayList;
import java.util.List;

import com.example.school.viewModels.TeacherRegistrationViewModel;
import com.example.school.viewModels.TeacherViewModel;
import com.example.school.viewModels.Interfaces.ViewModel;

public class TeacherVMValidator extends VMValidatorBase {
	private TeacherRegistrationViewModel teacher;
	
	public TeacherVMValidator() {
		super();
	}

	@Override
	public List<String> validate() {
		List<String> result = new ArrayList<String>();
		
		if (teacher == null) {
			result.add("Teacher validator not initialized");
			return result;
		}
		
		result.addAll(emailValidator.validateEmail(teacher.getEmail()));
		result.addAll(stringValidator.validateString(teacher.getName(), "Teacher name", 50));
		result.addAll(stringValidator.validateString(teacher.getPassword(), "Teacher password", null));
		
		return result;
	}

	@Override
	public boolean injectModel(ViewModel model) {
		if (model == null) {
			return false;
		}
		this.teacher = (TeacherRegistrationViewModel) model;
		
		return true;
	}

}

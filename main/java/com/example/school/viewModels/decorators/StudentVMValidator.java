package com.example.school.viewModels.decorators;

import java.util.ArrayList;
import java.util.List;

import com.example.school.viewModels.StudentRegistrationViewModel;
import org.springframework.stereotype.Component;

import com.example.school.viewModels.Interfaces.ViewModel;


@Component
public class StudentVMValidator extends VMValidatorBase {
	
	private StudentRegistrationViewModel student;
	
	public StudentVMValidator () {
		super();
	}

	@Override
	public List<String> validate() {
		List<String> result = new ArrayList<String>();
		
		if (student == null) {
			result.add("Invalid user data!");
			return result;
		}
		
		result.addAll(emailValidator.validateEmail(student.getEmail()));
		result.addAll(stringValidator.validateString(student.getName(), "The student's name", 50));
		result.addAll(stringValidator.validateString(student.getPassword(), "Student's passowrd", null));
		result.addAll(checkIfPasswordsMatch());
		return result;
	}

	@Override
	public boolean injectModel(ViewModel model) {
		if (model == null || model.isEmpty()) {
			return false;
		}
		
		student = (StudentRegistrationViewModel) model;
		
		return true;
	}

	private List<String> checkIfPasswordsMatch() {
		List<String> errors = new ArrayList<>();

		if (!student.getPassword().equals(student.getPasswordRepeat())) {
			errors.add("Passwords do not match!");
		}

		return errors;
	}
}

package com.example.school.viewModels.decorators;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.example.school.viewModels.StudentViewModel;
import com.example.school.viewModels.Interfaces.ViewModel;


@Component
public class StudentVMValidator extends VMValidatorBase {
	
	private StudentViewModel student;
	
	public StudentVMValidator () {
		super();
	}

	@Override
	public List<String> validate() {
		List<String> result = new ArrayList<String>();
		
		if (student == null) {
			result.add("Student validator not initialized");
			return result;
		}
		
		result.addAll(emailValidator.validateEmail(student.getEmail()));
		result.addAll(stringValidator.validateString(student.getName(), "The student's name", 50));
		result.addAll(stringValidator.validateString(student.getPassword(), "Student's passowrd", null));
		return result;
	}

	@Override
	public boolean injectModel(ViewModel model) {
		if (model == null || model.isEmpty()) {
			return false;
		}
		
		student = (StudentViewModel) model;
		
		return true;
	}

}

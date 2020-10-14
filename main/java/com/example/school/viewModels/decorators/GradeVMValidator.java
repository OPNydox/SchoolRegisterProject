package com.example.school.viewModels.decorators;

import java.util.ArrayList;
import java.util.List;

import com.example.school.viewModels.GradeViewModel;
import com.example.school.viewModels.ViewModel;

public class GradeVMValidator extends VMValidatorBase {

	private GradeViewModel grade;
	
	public GradeVMValidator () {
		super();
	}
	
	@Override
	public List<String> validate() {
		List<String> result = new ArrayList<>();
		
		if (grade == null) {
			result.add("Grade validator not initialised.");
			return result;
		}
		
		result.addAll(stringValidator.validateString(grade.getClassName(), "Course name", 50));
		result.addAll(emailValidator.validateEmail(grade.getStudentEmail()));
		result.addAll(numberValidator.validateNumber(grade.getMark()));
		
		return result;
	}

	@Override
	public boolean injectModel(ViewModel model) {
		// TODO Auto-generated method stub
		return false;
	}

}

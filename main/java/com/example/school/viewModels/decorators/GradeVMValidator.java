package com.example.school.viewModels.decorators;

import java.util.ArrayList;
import java.util.List;

import com.example.school.viewModels.GradeViewModel;
import com.example.school.viewModels.Interfaces.ViewModel;
import com.example.school.viewModels.decorators.dataValidators.IdValidator;

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
		
		result.addAll(idValidator.validate(grade.getCourseId()));
		result.addAll(idValidator.validate(grade.getStudentId()));
		result.addAll(numberValidator.validateNumber(grade.getMark()));
		
		return result;
	}

	@Override
	public boolean injectModel(ViewModel model) {
		this.grade = (GradeViewModel) model;
		return false;
	}

}

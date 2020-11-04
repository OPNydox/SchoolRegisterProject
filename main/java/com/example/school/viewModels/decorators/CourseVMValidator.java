package com.example.school.viewModels.decorators;

import java.util.ArrayList;
import java.util.List;

import com.example.school.viewModels.CourseViewModel;
import com.example.school.viewModels.Interfaces.ViewModel;


public class CourseVMValidator extends VMValidatorBase {

	private CourseViewModel course;
	
	
	@Override
	public List<String> validate() {
		List<String> result = new ArrayList<>();
		
		result.addAll(stringValidator.validateString(course.getName(), "Course name", 30));
		result.addAll(stringValidator.validateString(course.getSubject(), "Course subject", 50));
		result.addAll(numberValidator.validateNumber(course.getHonorarium()));
		
		return result;
	}

	@Override
	public boolean injectModel(ViewModel model) {
		if (model == null) {
			return false;
		}
		
		course = (CourseViewModel) model;
		return true;
	}

}

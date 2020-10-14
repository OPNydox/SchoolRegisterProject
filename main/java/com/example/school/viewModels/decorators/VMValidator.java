package com.example.school.viewModels.decorators;

import java.util.List;

import com.example.school.viewModels.ViewModel;

public interface VMValidator {
	List<String> validate();
	
	boolean injectModel(ViewModel model);

}

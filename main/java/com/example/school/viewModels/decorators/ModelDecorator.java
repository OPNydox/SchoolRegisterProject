package com.example.school.viewModels.decorators;

import java.util.ArrayList;
import java.util.List;

import com.example.school.viewModels.Interfaces.ViewModel;


public class ModelDecorator {
	private ViewModel model;
	
	public ModelDecorator(ViewModel model) {
		this.setModel(model);
	}
	
	public List<String> validateModel(VMValidator validator) {
		List<String> result;
		validator.injectModel(model);
		result = validator.validate();
		return result;
	}

	public ViewModel getModel() {
		return model;
	}

	public void setModel(ViewModel model) {
		this.model = model;
	}
	
	
}

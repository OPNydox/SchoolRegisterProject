package com.example.school.viewModels.decorators;

import java.util.ArrayList;
import java.util.List;

import com.example.school.viewModels.PresenceViewModel;
import com.example.school.viewModels.ViewModel;

public class PresenceVMValidator implements VMValidator{
	
	private PresenceViewModel presence;
	
	@Override
	public List<String> validate() {
		List<String> result = new ArrayList<>();
		
		if (presence == null) {
			result.add("Presence validator not initialized.");
			return result;
		}
		
		return result;
	}

	@Override
	public boolean injectModel(ViewModel model) {
		if (model == null) {
			return false;
		}
		
		presence = (PresenceViewModel) model;
		return true;
	}

}

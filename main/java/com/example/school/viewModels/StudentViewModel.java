package com.example.school.viewModels;

import com.example.school.utilities.enums.UserRole;
import com.example.school.viewModels.Interfaces.UserViewModel;
import com.example.school.viewModels.Interfaces.ViewModel;

import java.util.Set;

public class StudentViewModel extends UserViewModel {
	private Set<PresenceViewModel> presences;

	public StudentViewModel() {
		super();
		super.setUserRole(UserRole.STUDENT);
	}

	public Set<PresenceViewModel> getPresences() {
		return presences;
	}

	public void setPresences(Set<PresenceViewModel> presences) {
		this.presences = presences;
	}
}

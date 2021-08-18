package com.example.school.services.interfaces;

import java.util.List;

import com.example.school.database.entities.User;
import com.example.school.viewModels.Interfaces.UserViewModel;
import com.example.school.viewModels.ViewModelPairs.UserCourseIdPair;

public interface IUserService {
	UserViewModel findUserByUsername(String username);

	List<String> enrollUserInClass(UserCourseIdPair userClassIdPair);
}

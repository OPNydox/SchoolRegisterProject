package com.example.school.services.interfaces;

import java.util.List;

import com.example.school.database.entities.User;
import com.example.school.viewModels.ViewModelPairs.UserCourseIdPair;

public interface IUserService {
	User findUserByUsername(String username);

	List<String> enrollUserInClass(UserCourseIdPair userClassIdPair);
}

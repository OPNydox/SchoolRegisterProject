package com.example.school.services.interfaces;

import com.example.school.database.entities.User;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.viewModels.Interfaces.ViewModel;
import com.example.school.viewModels.ViewModelPairs.UserCourseIdPair;

public interface IUserService {
	User findUserByUsername(String username);

	ServiceReturnResult<User> createUser(ViewModel viewModel);

	ServiceReturnResult enrollUserInClass(UserCourseIdPair userClassIdPair);
}

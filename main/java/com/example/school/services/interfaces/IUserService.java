package com.example.school.services.interfaces;

import com.example.school.database.entities.User;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.viewModels.Interfaces.UserViewModel;
import com.example.school.viewModels.ViewModelPairs.UserCourseIdPair;

public interface IUserService {
	UserViewModel findUserVMByUsername(String username);

	User findUserByUsername(String username);

	ServiceReturnResult<UserViewModel> enrollUserInClass(UserViewModel user, Long courseId);
}

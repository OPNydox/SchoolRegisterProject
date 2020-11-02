package com.example.school.services.interfaces;

import com.example.school.database.entities.User;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.viewModels.ViewModel;

public interface IUserService {
	User findUserByUsername(String username);

	ServiceReturnResult createUser(ViewModel viewModel);
}

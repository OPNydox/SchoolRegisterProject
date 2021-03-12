package com.example.school.services.interfaces;

import com.example.school.database.entities.AuthGroup;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.viewModels.Interfaces.UserViewModel;

public interface IAuthGroupService {
    ServiceReturnResult<AuthGroup> addAuth(UserViewModel viewModel);
}

package com.example.school.services.interfaces;

import com.example.school.utilities.ServiceReturnResult;
import com.example.school.viewModels.AuthViewModel;
import com.example.school.viewModels.Interfaces.ViewModel;

public interface IAuthGroupService {
    ServiceReturnResult addAuth(ViewModel viewModel);
}

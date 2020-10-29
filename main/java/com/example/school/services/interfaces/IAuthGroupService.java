package com.example.school.services.interfaces;

import com.example.school.utilities.ServiceReturnResult;
import com.example.school.viewModels.AuthViewModel;

public interface IAuthGroupService {
    ServiceReturnResult addAuth(AuthViewModel authModel);
}

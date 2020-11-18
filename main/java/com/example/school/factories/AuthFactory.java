package com.example.school.factories;

import com.example.school.database.entities.AuthGroup;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.viewModels.AuthViewModel;

import org.springframework.stereotype.Component;

@Component
public class AuthFactory {
    public ServiceReturnResult<AuthGroup> getEntity(AuthViewModel authViewModel) {
        AuthGroup authGroup = new AuthGroup();
        ServiceReturnResult<AuthGroup> authReturnResult = new ServiceReturnResult<>();

        authGroup.setEmail(authViewModel.getEmail());
        authGroup.setAuthGroup(authViewModel.getUserRole());

        authReturnResult.setReturnResultObject(authGroup);

        return authReturnResult;
    }
}

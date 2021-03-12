package com.example.school.factories;

import com.example.school.database.entities.AuthGroup;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.utilities.StringConstants;
import com.example.school.viewModels.Interfaces.UserViewModel;

import org.springframework.stereotype.Component;

@Component
public class AuthFactory {

    public ServiceReturnResult<AuthGroup> getEntity(UserViewModel userViewModel) {
        AuthGroup authGroup = new AuthGroup();
        ServiceReturnResult<AuthGroup> authReturnResult = new ServiceReturnResult<>();

        authGroup.setEmail(userViewModel.getEmail());
        authGroup.setAuthGroup(StringConstants.ROLE_PREFIX + userViewModel.getRole());

        authReturnResult.setReturnResultObject(authGroup);

        return authReturnResult;
    }
}

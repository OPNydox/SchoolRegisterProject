package com.example.school.factories;

import com.example.school.database.entities.AuthGroup;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.viewModels.AuthViewModel;

import org.springframework.stereotype.Component;

@Component
public class AuthFactory {
    private ServiceReturnResult returnResult;

    private AuthViewModel authViewModel;

    public ServiceReturnResult getEntity(AuthViewModel authViewModel) {
        AuthGroup authGroup = new AuthGroup();

        initialize(authViewModel);

        authGroup.setEmail(authViewModel.getEmail());
        authGroup.setAuthGroup(authViewModel.getUserRole());

        this.returnResult.setReturnResultObject(authGroup);
        
        return this.returnResult;
    }

    private void initialize(AuthViewModel authViewModel) {
        this.returnResult = new ServiceReturnResult();
        this.authViewModel = authViewModel;
    }
}

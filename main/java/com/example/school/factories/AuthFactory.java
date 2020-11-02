package com.example.school.factories;

import com.example.school.database.entities.AuthGroup;
import com.example.school.factories.interfaces.EntityFactory;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.viewModels.AuthViewModel;
import com.example.school.viewModels.ViewModel;

import org.springframework.stereotype.Component;

@Component
public class AuthFactory implements EntityFactory {
    private ServiceReturnResult returnResult;

    private AuthViewModel authViewModel;

    @Override
    public ServiceReturnResult getEntity(ViewModel viewModel) {
        AuthGroup authGroup = new AuthGroup();

        initialize(authViewModel);

        authGroup.setEmail(authViewModel.getEmail());
        authGroup.setAuthGroup(authViewModel.getUserRole());

        this.returnResult.setReturnResultObject(authGroup);

        return this.returnResult;
    }

    private void initialize(ViewModel authViewModel) {
        this.returnResult = new ServiceReturnResult();
        this.authViewModel = (AuthViewModel) authViewModel;
    }
}

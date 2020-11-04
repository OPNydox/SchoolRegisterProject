package com.example.school.factories;

import com.example.school.database.entities.AuthGroup;
import com.example.school.factories.interfaces.ModelFactory;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.viewModels.AuthViewModel;
import com.example.school.viewModels.Interfaces.ViewModel;

import org.springframework.stereotype.Component;

@Component
public class AuthFactory implements ModelFactory {
    private ServiceReturnResult returnResult;

    private AuthViewModel authViewModel;

    @Override
    public ServiceReturnResult getEntity(ViewModel viewModel) {
        AuthGroup authGroup = new AuthGroup();

        initialize(viewModel);

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

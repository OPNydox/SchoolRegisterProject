package com.example.school.factories;

import com.example.school.factories.interfaces.ModelFactory;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.viewModels.AuthViewModel;
import com.example.school.viewModels.Interfaces.IUserViewModel;
import com.example.school.viewModels.Interfaces.ViewModel;

import org.springframework.stereotype.Component;

@Component
public class AuthVMFactory implements ModelFactory {

    private ServiceReturnResult returnResult;

    private IUserViewModel userViewModel;

    @Override
    public ServiceReturnResult getEntity(ViewModel viewModel) {
        AuthViewModel resultModel = new AuthViewModel();

        initilize(viewModel);

        resultModel.setEmail(userViewModel.getEmail());
        resultModel.setUserRole(userViewModel.getRole());

        this.returnResult.setReturnResultObject(resultModel);
        
        return this.returnResult;
    }

    private void initilize(ViewModel viewModel) {
        this.returnResult = new ServiceReturnResult();
        this.userViewModel = (IUserViewModel) viewModel;
    }
    
}

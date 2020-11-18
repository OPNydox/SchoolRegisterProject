package com.example.school.factories;

import com.example.school.utilities.ServiceReturnResult;
import com.example.school.viewModels.AuthViewModel;
import com.example.school.viewModels.Interfaces.UserViewModel;

import org.springframework.stereotype.Component;

@Component
public class AuthVMFactory {

    public ServiceReturnResult<AuthViewModel> getEntity(UserViewModel userViewModel) {
        AuthViewModel resultModel = new AuthViewModel();
        ServiceReturnResult<AuthViewModel> returnResult = new ServiceReturnResult<>();

        resultModel.setEmail(userViewModel.getEmail());
        resultModel.setUserRole(userViewModel.getRole());

        returnResult.setReturnResultObject(resultModel);
        
        return returnResult;
    }
}

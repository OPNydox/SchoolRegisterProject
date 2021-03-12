package com.example.school.services.implementations;

import com.example.school.database.entities.AuthGroup;
import com.example.school.factories.AuthFactory;
import com.example.school.repositories.AuthGroupRepository;
import com.example.school.services.interfaces.IAuthGroupService;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.viewModels.Interfaces.UserViewModel;
import com.example.school.viewModels.decorators.UserVMDecorator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthGroupService implements IAuthGroupService {
    @Autowired
    private AuthGroupRepository authRepository;

    @Autowired
    private AuthFactory authFactory;

    @Override
    public ServiceReturnResult<AuthGroup> addAuth(UserViewModel userViewModel) {
        ServiceReturnResult<AuthGroup> authResult = new ServiceReturnResult<>();
        ServiceReturnResult<Void> validatorResult = new ServiceReturnResult<>();
        UserVMDecorator decorator = new UserVMDecorator();

        decorator.injectModel(userViewModel);
        validatorResult.addErrorMsg(decorator.validate());

        if (validatorResult.hasErrors()) {
            authResult.addErrorMsg(validatorResult.getErrorMessages());
            return authResult;
        }

        authResult = this.authFactory.getEntity(userViewModel);

        if (authResult.hasErrors()) {
            return authResult;
        }

        saveEntity(authResult.getReturnResultObject());

        return authResult;
    }

    private void saveEntity(AuthGroup entity) {
        authRepository.save(entity);
    }
    
}

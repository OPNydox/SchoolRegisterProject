package com.example.school.services.implementations;

import com.example.school.database.entities.AuthGroup;
import com.example.school.factories.AuthFactory;
import com.example.school.factories.AuthVMFactory;
import com.example.school.repositories.AuthGroupRepository;
import com.example.school.services.interfaces.IAuthGroupService;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.viewModels.AuthViewModel;
import com.example.school.viewModels.Interfaces.ViewModel;
import com.example.school.viewModels.decorators.AuthGroupValidator;
import com.example.school.viewModels.decorators.UserVMDecorator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthGroupService implements IAuthGroupService {
    @Autowired
    private AuthGroupRepository authRepository;

    @Autowired
    private AuthVMFactory authVMFactory;

    @Autowired
    private AuthFactory authFactory;

    @Override
    public ServiceReturnResult addAuth(ViewModel viewModel) {
        ServiceReturnResult factoryResult = new ServiceReturnResult();
        ServiceReturnResult vmFactoryResult = new ServiceReturnResult();
        ServiceReturnResult validatorResult = new ServiceReturnResult();
        UserVMDecorator decorator = new UserVMDecorator();

        decorator.injectModel(viewModel);
        validatorResult.addErrorMsg(decorator.validate());

        if (!validatorResult.isSuccessful()) {
            return validatorResult;
        }

        vmFactoryResult = authVMFactory.getEntity(viewModel);

        if (!vmFactoryResult.isSuccessful()) {
            return vmFactoryResult;
        }

        ViewModel userViewModel = (ViewModel) vmFactoryResult.getReturnResultObject();

        factoryResult = this.authFactory.getEntity(userViewModel);

        if (!validatorResult.isSuccessful()) {
            return factoryResult;
        }

        saveEntity( (AuthGroup) factoryResult.getReturnResultObject());

        return factoryResult;
    }

    private void saveEntity(AuthGroup entity) {
        authRepository.save(entity);
    }
    
}

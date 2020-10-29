package com.example.school.services.implementations;

import com.example.school.database.entities.AuthGroup;
import com.example.school.factories.AuthFactory;
import com.example.school.repositories.AuthGroupRepository;
import com.example.school.services.interfaces.IAuthGroupService;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.viewModels.AuthViewModel;
import com.example.school.viewModels.decorators.AuthGroupValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties.Validation;
import org.springframework.stereotype.Service;

@Service
public class AuthGroupService implements IAuthGroupService {
    @Autowired
    private AuthGroupRepository authRepository;

    @Autowired
    private AuthFactory authFactory;

    @Override
    public ServiceReturnResult addAuth(AuthViewModel authModel) {
        ServiceReturnResult factoryResult = new ServiceReturnResult();
        ServiceReturnResult validatorResult = new ServiceReturnResult();
        AuthGroupValidator decorator = new AuthGroupValidator();

        decorator.injectModel(authModel);
        validatorResult.addErrorMsg(decorator.validate());

        if (!validatorResult.isSuccessful()) {
            return validatorResult;
        }

        factoryResult = this.authFactory.getEntity(authModel);

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

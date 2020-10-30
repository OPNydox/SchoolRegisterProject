package com.example.school.viewModels.decorators.dataValidators;

import java.security.Provider.Service;

import com.example.school.utilities.PasswordPair;
import com.example.school.utilities.ServiceReturnResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PasswordValidator {
    @Autowired
    private StringValidator stringValidator;

    public ServiceReturnResult validatePasswords(PasswordPair passwordPair) {
        ServiceReturnResult result = new ServiceReturnResult();

        result.addErrorMsg(stringValidator.validateString(passwordPair.getPassword(), null, null));
        result.addErrorMsg(stringValidator.validateString(passwordPair.getRepeatPassword(), null, null));

        if (!result.isSuccessful()) {
            return result;
        }

        result.addErrorMsg(checkIfPasswordsMatch(passwordPair).getErrorMessages());

        return result;
    }

    private ServiceReturnResult checkIfPasswordsMatch(PasswordPair pair) {
        ServiceReturnResult result = new ServiceReturnResult();
        if (pair.getPassword().equals(pair.getRepeatPassword())) {
            result.addErrorMsg("Passwords do not match");
        }

        return result;
    }
}

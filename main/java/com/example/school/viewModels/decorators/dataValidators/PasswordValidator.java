package com.example.school.viewModels.decorators.dataValidators;

import java.util.ArrayList;
import java.util.List;

import com.example.school.utilities.PasswordPair;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PasswordValidator {
    @Autowired
    private StringValidator stringValidator;

    public List<String> validatePasswords(PasswordPair passwordPair) {
        List<String> result = new ArrayList<>();

        result.addAll(stringValidator.validateString(passwordPair.getPassword(), null, null));
        result.addAll(stringValidator.validateString(passwordPair.getRepeatPassword(), null, null));

        if (!result.isEmpty()) {
            return result;
        }

        result.addAll(checkIfPasswordsMatch(passwordPair));

        return result;
    }

    private List<String> checkIfPasswordsMatch(PasswordPair pair) {
        List<String> result = new ArrayList<>();
        if (pair.getPassword().equals(pair.getRepeatPassword())) {
            result.add("Passwords do not match");
        }

        return result;
    }
}

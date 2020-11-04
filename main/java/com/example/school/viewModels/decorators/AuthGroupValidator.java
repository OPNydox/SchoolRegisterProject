package com.example.school.viewModels.decorators;

import java.util.ArrayList;
import java.util.List;

import com.example.school.database.entities.AuthGroup;
import com.example.school.viewModels.AuthViewModel;
import com.example.school.viewModels.Interfaces.ViewModel;


public class AuthGroupValidator extends VMValidatorBase {

    private AuthViewModel vmModel;

    @Override
    public List<String> validate() {
        List<String> errors = new ArrayList<>();

        errors.addAll(stringValidator.validateString(vmModel.getEmail(), "User email", 50));
        errors.addAll(stringValidator.validateRoleString(vmModel.getUserRole()));

        return errors;
    }

    @Override
    public boolean injectModel(ViewModel model) {
        if (model == null) {
            return false;
        }

        vmModel = (AuthViewModel) model;

        return true;
    }
    
}

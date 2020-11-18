package com.example.school.viewModels.decorators;

import java.util.ArrayList;
import java.util.List;

import com.example.school.viewModels.Interfaces.UserViewModel;
import com.example.school.viewModels.Interfaces.ViewModel;

public class UserVMDecorator extends VMValidatorBase {

    private UserViewModel userViewModel;

    @Override
    public List<String> validate() {
        List<String> errors = new ArrayList<>();

        if (userViewModel == null) {
            errors.add("User model not found.");
            return errors;
        }
        errors.addAll(stringValidator.validateString(userViewModel.getEmail(), "Email", 30));
        
        if (userViewModel.getRole() == null) {
            errors.add("User role not found.");
            return errors;
        }

        return errors;
    }

    @Override
    public boolean injectModel(ViewModel model) {
        this.userViewModel = (UserViewModel) model;
        return true;
    }
    
}

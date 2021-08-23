package com.example.school.viewModels.decorators;

import com.example.school.viewModels.Interfaces.ViewModel;

import java.util.List;

public class TeacherRegistrationVMValidator extends VMValidatorBase {
    @Override
    public List<String> validate() {
        return null;
    }

    @Override
    public boolean injectModel(ViewModel model) {
        return false;
    }
}

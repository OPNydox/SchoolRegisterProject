package com.example.school.factories;

import com.example.school.database.entities.Teacher;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.viewModels.TeacherRegistrationViewModel;
import com.example.school.viewModels.TeacherViewModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class TeacherFactory {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ServiceReturnResult<Teacher> getEntity(TeacherRegistrationViewModel teacherViewModel) {
        Teacher teacherEntity = new Teacher();
        ServiceReturnResult<Teacher> teacherResult = new ServiceReturnResult<>(); 

        teacherEntity.setEmail(teacherViewModel.getEmail());
        teacherEntity.setName(teacherViewModel.getName());
        teacherEntity.setSalary(teacherViewModel.getSalary());
        teacherEntity.setPassword(getPassword(teacherViewModel.getPassword()));

        teacherResult.setReturnResultObject(teacherEntity);

        return teacherResult;
    }

    private String getPassword(String password) {
        String encodedPassword;

        encodedPassword = passwordEncoder.encode(password);

        return encodedPassword;
    }
    
}

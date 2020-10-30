package com.example.school.factories;

import com.example.school.database.entities.Teacher;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.viewModels.TeacherViewModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class TeacherFactory {
    private ServiceReturnResult returnResult;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private TeacherViewModel teacherViewModel;

    public ServiceReturnResult getEntity(TeacherViewModel teacherViewModel) {
        Teacher teacherEntity = new Teacher();

        initialize(teacherViewModel);

        teacherEntity.setEmail(teacherViewModel.getEmail());
        teacherEntity.setName(teacherViewModel.getName());
        teacherEntity.setPassword(getPassword());
        teacherEntity.setStudent(false);

        this.returnResult.setReturnResultObject(teacherEntity);

        return this.returnResult;
    }

    private void initialize(TeacherViewModel teacherViewModel) {
        this.teacherViewModel = teacherViewModel;
        returnResult = new ServiceReturnResult();
    }

    private String getPassword() {
        String encodedPassword;

        encodedPassword = passwordEncoder.encode(teacherViewModel.getPassword());

        return encodedPassword;
    }
    
}

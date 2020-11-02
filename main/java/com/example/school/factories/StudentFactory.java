package com.example.school.factories;

import java.util.ArrayList;

import com.example.school.database.entities.Course;
import com.example.school.database.entities.Grade;
import com.example.school.database.entities.Presence;
import com.example.school.database.entities.Student;
import com.example.school.factories.interfaces.EntityFactory;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.viewModels.StudentViewModel;
import com.example.school.viewModels.ViewModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class StudentFactory implements EntityFactory {
    @Autowired
    private PasswordEncoder passwordEncoder;

    private ServiceReturnResult studentResult;

    private StudentViewModel studentViewModel;

    @Override
    public ServiceReturnResult getEntity(ViewModel viewModel) {
        Student student = new Student();

        initialize(viewModel);

        student.setEmail(this.studentViewModel.getEmail());
        student.setName(this.studentViewModel.getName());
        student.setCourses(new ArrayList<Course>());
        student.setGrades(new ArrayList<Grade>());
        student.setPassword(getPassword());
        student.setPresences(new ArrayList<Presence>());
        student.setStudent(true);

        this.studentResult.setReturnResultObject(student);

        return this.studentResult;
    }
    
    private void initialize(ViewModel viewModel) {
        this.studentResult = new ServiceReturnResult();
        this.studentViewModel = (StudentViewModel) viewModel;
    }

    private String getPassword() {
        return this.passwordEncoder.encode(studentViewModel.getPassword());
    }
}

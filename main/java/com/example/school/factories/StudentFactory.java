package com.example.school.factories;

import java.util.ArrayList;
import java.util.HashSet;

import com.example.school.database.entities.Course;
import com.example.school.database.entities.Grade;
import com.example.school.database.entities.Presence;
import com.example.school.database.entities.Student;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.viewModels.RegistrationViewModel;
import com.example.school.viewModels.StudentViewModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class StudentFactory {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ServiceReturnResult<Student> createStudentEntity(RegistrationViewModel studentViewModel) {
        Student student = new Student();
        ServiceReturnResult<Student> studentResult = new ServiceReturnResult<>();

        student.setEmail(studentViewModel.getEmail());
        student.setName(studentViewModel.getName());
        student.setCourses(new HashSet<>());
        student.setGrades(new HashSet<>());
        student.setPassword(getPassword(studentViewModel.getPassword()));
        student.setPresences(new HashSet<>());
        student.setStudent(true);

        studentResult.setReturnResultObject(student);

        return studentResult;
    }

    private String getPassword(String password) {
        return this.passwordEncoder.encode(password);
    }
}

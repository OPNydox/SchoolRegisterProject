package com.example.school.viewModels;

import com.example.school.utilities.enums.UserRole;
import com.example.school.viewModels.Interfaces.UserViewModel;

public class TeacherRegistrationViewModel extends UserViewModel {
    String password;

    String passwordRepeat;

    String error;

    double salary;

    {
        this.setUserRole(UserRole.TEACHER);
    }

    public TeacherRegistrationViewModel(String name, String email, String password, String passwordRepeat, double salary) {
        super(email, name);
        this.setPassword(password);
        this.setPasswordRepeat(passwordRepeat);
        this.setSalary(salary);
    }

    public TeacherRegistrationViewModel() {}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}

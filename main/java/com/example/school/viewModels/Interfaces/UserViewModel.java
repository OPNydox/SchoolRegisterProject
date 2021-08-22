package com.example.school.viewModels.Interfaces;

import com.example.school.utilities.enums.UserRole;
import com.example.school.viewModels.CourseViewModel;
import com.example.school.viewModels.GradeViewModel;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserViewModel extends ViewModel {
    private String id;

    private String email;

    private String name;

    private UserRole role;

    private String password;

    private Set<CourseViewModel> courses = new HashSet<>();

    private Set<GradeViewModel> grades = new HashSet<>();

    public UserViewModel() {}

    public UserViewModel(String email, String name) {
        setEmail(email);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public UserRole getRole() {
        return role;
    }

    public void setUserRole(UserRole userRole) {
        this.role = userRole;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<CourseViewModel> getCourses() {
        return courses;
    }

    public void setCourses(Set<CourseViewModel> courses) {
        this.courses = courses;
    }

    public Set<GradeViewModel> getGrades() {
        return grades;
    }

    public void setGrades(Set<GradeViewModel> grades) {
        this.grades = grades;
    }
}

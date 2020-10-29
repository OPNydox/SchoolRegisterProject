package com.example.school.viewModels;

import com.example.school.utilities.enums.UserRole;

public class AuthViewModel extends ViewModel {

    private String rolePrefix = "ROLE_";

    private boolean isEmpty;

    private String email;

    private String userRole;

    public AuthViewModel(String email, UserRole role) {
        this.setEmail(email);
        this.setUserRole(role);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = rolePrefix + userRole.name();
    }

    @Override
    public boolean isEmpty() {
        return this.isEmpty;
    }

    @Override
    public boolean setEmpty() {
        this.isEmpty = true;
        return true;
    }
    
}

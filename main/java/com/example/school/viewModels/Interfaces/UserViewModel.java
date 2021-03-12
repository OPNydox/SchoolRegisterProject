package com.example.school.viewModels.Interfaces;

import com.example.school.utilities.enums.UserRole;

public abstract class UserViewModel extends ViewModel {
    private String email;

    private String name;

    private UserRole role;

    public UserViewModel() {}

    public UserViewModel(String email, String name) {
        setEmail(email);
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

    protected void setUserRole(UserRole userRole) {
        this.role = userRole;
    }
}

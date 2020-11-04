package com.example.school.viewModels.Interfaces;

import com.example.school.utilities.enums.UserRole;

public interface IUserViewModel {
    UserRole getRole();

    String getEmail();
}
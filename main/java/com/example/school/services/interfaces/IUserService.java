package com.example.school.services.interfaces;

import com.example.school.database.entities.User;

public interface IUserService {
	User findUserByUsername(String username);
}

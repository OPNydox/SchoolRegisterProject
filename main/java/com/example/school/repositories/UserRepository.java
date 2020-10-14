package com.example.school.repositories;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.example.school.database.entities.User;

@NoRepositoryBean
public interface UserRepository<T extends User> extends CrudRepository<User, Long> {
	public User findByEmail(String email);
}

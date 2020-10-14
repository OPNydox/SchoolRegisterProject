package com.example.school.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.school.database.entities.AuthGroup;

public interface AuthGroupRepository extends CrudRepository<AuthGroup, Long> {
	List<AuthGroup> findByEmail(String email);
}

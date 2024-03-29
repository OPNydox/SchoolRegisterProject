package com.example.school.repositories;

import java.io.Serializable;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.example.school.database.entities.User;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository<T extends User> extends CrudRepository<User, Long> {
	User findByEmail(String email);

	@Query(value = "select * from User where isStudent = true", nativeQuery = true)
	Set<User> findAllStudents();

	@Query(value = "select u from User u where u.userType = :user_type and u.isEmpty = false")
	Set<User> findAllUsersByType(@Param("user_type") int user_type);
}

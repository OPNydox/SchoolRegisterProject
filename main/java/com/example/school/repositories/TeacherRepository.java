package com.example.school.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.school.database.entities.Teacher;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {
	
	Teacher findByEmail(String email);
}

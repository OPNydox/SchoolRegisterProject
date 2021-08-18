/*package com.example.school.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.school.database.entities.Student;

@Repository
public interface StudentRepository extends CrudRepository <Student, Long>{
	
	@Query(value = "select s from Student s where s.email = :email")
	Student findByEmail(String email);

}*/

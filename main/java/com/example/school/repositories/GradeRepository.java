package com.example.school.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.school.database.entities.Grade;

public interface GradeRepository extends CrudRepository<Grade, Long> {

}

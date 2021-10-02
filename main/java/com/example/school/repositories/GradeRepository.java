package com.example.school.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.school.database.entities.Grade;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface GradeRepository extends CrudRepository<Grade, Long> {

}

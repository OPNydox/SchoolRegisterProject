package com.example.school.repositories;

import java.io.Serializable;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.school.database.entities.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

	Course save(Course entity);
	
	@Query("select c from Course c where c.courseName = :course_name")
	Course findByName(@Param("course_name")String course_name);

	@Query("select u from User u join fetch u.courses where u.email = :user_email")
	Set<Course> findCoursesByStudentEmail(@Param("user_email") String user_email);
}

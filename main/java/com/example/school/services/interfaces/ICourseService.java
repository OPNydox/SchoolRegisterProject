package com.example.school.services.interfaces;

import java.util.Set;

import com.example.school.database.entities.Course;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.viewModels.CourseViewModel;

public interface ICourseService {
	
	ServiceReturnResult<Course> addCourse(CourseViewModel course);
	
	Course getCourseByName(String courseName);

	ServiceReturnResult<Course> getCourseById(Long id);

	ServiceReturnResult<CourseViewModel> getCourseVMById(Long id);
	
	void saveCourse(Course course);

	Set<CourseViewModel> getAllCoursesForPerson(String personEmail);

	Set<CourseViewModel> getAllCourses();

	boolean isUserEnrolledInCourse(Course course, Long userId);
}

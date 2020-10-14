package com.example.school.services.interfaces;

import java.util.List;

import com.example.school.database.entities.Course;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.viewModels.CourseViewModel;

public interface ICourseService {
	
	Course addCourse(CourseViewModel course);
	
	Course getCourseByName(String courseName);

	ServiceReturnResult getCourseById(String id);

	ServiceReturnResult getCourseVMById(String id);
	
	void saveCourse(Course course);

	List<CourseViewModel> getAllCoursesForPerson(String personEmail);

	List<CourseViewModel> getAllCourses();
}

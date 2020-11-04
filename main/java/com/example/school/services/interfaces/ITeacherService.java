package com.example.school.services.interfaces;

import com.example.school.database.entities.Course;
import com.example.school.database.entities.Teacher;
import com.example.school.exceptions.EntityException;
import com.example.school.exceptions.ValueException;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.viewModels.TeacherViewModel;
import com.example.school.viewModels.ViewModelPairs.TeacherCoursePair;

public interface ITeacherService {
	ServiceReturnResult addTeacher(TeacherViewModel teacherView);

	Teacher addTeacher(Teacher teacher);
	
	ServiceReturnResult entollTeacherInCourse(TeacherCoursePair teacherCoursePair);
	
	Teacher findTeacherByEmail(String email) throws ValueException;
	
	boolean addTeacherToCourse(Teacher teacher, Course course) throws ValueException;
	
	boolean addTeacherToCourse(String teacherEmail, String courseName) throws EntityException;
}

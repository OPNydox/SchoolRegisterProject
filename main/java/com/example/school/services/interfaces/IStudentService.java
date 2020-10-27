package com.example.school.services.interfaces;

import java.security.Provider.Service;
import java.util.List;

import com.example.school.database.entities.Student;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.utilities.StudentCoursePair;
import com.example.school.viewModels.StudentViewModel;

public interface IStudentService {
	Student createStudent(StudentViewModel student);
	
	Student findStudentByEmail(String email);

	ServiceReturnResult findStudentById(String id);
	
	ServiceReturnResult findStudentEntityById(String id);
	
	List<Student> findAllStudents();

	ServiceReturnResult enlistStudentInCourse(StudentCoursePair studentCoursePair);
}

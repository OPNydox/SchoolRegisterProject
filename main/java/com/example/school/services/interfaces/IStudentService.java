package com.example.school.services.interfaces;

import java.security.Provider.Service;
import java.util.List;

import com.example.school.database.entities.Student;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.utilities.StudentCoursePair;
import com.example.school.viewModels.StudentViewModel;

public interface IStudentService {
	ServiceReturnResult<Student> createStudent(StudentViewModel student);

	Student createStudent(Student student);
	
	Student findStudentByEmail(String email);

	ServiceReturnResult<StudentViewModel> findStudentById(String id);
	
	ServiceReturnResult<Student> findStudentEntityById(String id);
	
	List<Student> findAllStudents();

	List<String> enlistStudentInCourse(StudentCoursePair studentCoursePair);
}

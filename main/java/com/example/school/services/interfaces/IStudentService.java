package com.example.school.services.interfaces;

import java.util.List;

import com.example.school.database.entities.Student;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.utilities.StudentCoursePair;
import com.example.school.viewModels.StudentViewModel;

public interface IStudentService {
	Student createStudent(StudentViewModel student);
	
	Student findStudentByEmail(String email);
	
	List<Student> findAllStudents();

	ServiceReturnResult enlistStudentInCourse(StudentCoursePair studentCoursePair);
}

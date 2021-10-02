package com.example.school.services.interfaces;

import java.util.List;

import com.example.school.database.entities.Student;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.utilities.StudentCoursePair;
import com.example.school.viewModels.StudentProfileViewModel;
import com.example.school.viewModels.StudentRegistrationViewModel;
import com.example.school.viewModels.StudentViewModel;

public interface IStudentService {
	ServiceReturnResult<Student> createStudent(StudentRegistrationViewModel studentCredentials);

	Student createStudent(Student student);
	
	Student findStudentByEmail(String email);

	ServiceReturnResult<StudentViewModel> findStudentById(Long id);
	
	ServiceReturnResult<Student> findStudentEntityById(Long id);
	
	List<Student> findAllStudents();

	List<String> enlistStudentInCourse(StudentCoursePair studentCoursePair);

	StudentProfileViewModel getStudentProfileViewModel(StudentViewModel student);
}

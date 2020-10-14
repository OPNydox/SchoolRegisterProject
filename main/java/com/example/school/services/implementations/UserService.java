package com.example.school.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.school.database.entities.User;
import com.example.school.exceptions.ValueException;
import com.example.school.services.interfaces.IStudentService;
import com.example.school.services.interfaces.ITeacherService;
import com.example.school.services.interfaces.IUserService;
import com.example.school.utilities.Verificator;
import com.example.school.utilities.interfaces.IWriter;

@Service
public class UserService implements IUserService{
	
	@Autowired
	private IStudentService studentService;
	
	@Autowired
	private ITeacherService teacherService;
	
	@Autowired
	private IWriter writer;

	
	public User findUserByUsername(String username) {
		User userFound;
		
		userFound = studentService.findStudentByEmail(username);
		
		if (Verificator.isEmpty(userFound)) {
			findTeacher(username);
		}
		
		return userFound;
	}
	
	private User findTeacher(String username) {
		User teacherFound = new User();
		try {
			teacherFound = teacherService.findTeacherByEmail(username);
		} catch (ValueException e) {
			writer.writeError(e.getMessage());
			teacherFound.setEmpty();
		}
		
		return teacherFound;
	}

}

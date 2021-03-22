package com.example.school.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.example.school.database.entities.Student;
import com.example.school.database.entities.Teacher;
import com.example.school.database.entities.User;
import com.example.school.exceptions.ValueException;
import com.example.school.factories.StudentFactory;
import com.example.school.factories.TeacherFactory;
import com.example.school.repositories.UserRepository;
import com.example.school.services.interfaces.IStudentService;
import com.example.school.services.interfaces.ITeacherService;
import com.example.school.services.interfaces.IUserService;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.utilities.StudentCoursePair;
import com.example.school.utilities.UserEntityHelper;
import com.example.school.utilities.Verificator;
import com.example.school.utilities.interfaces.IWriter;
import com.example.school.viewModels.Interfaces.ViewModel;
import com.example.school.viewModels.ViewModelPairs.TeacherCoursePair;
import com.example.school.viewModels.ViewModelPairs.UserCourseIdPair;
import com.example.school.viewModels.decorators.ModelDecorator;
import com.example.school.viewModels.decorators.StudentVMValidator;
import com.example.school.viewModels.decorators.TeacherVMValidator;
import com.example.school.viewModels.decorators.VMValidator;

@Service
public class UserService implements IUserService {

	@Autowired
	private IStudentService studentService;

	@Autowired
	private ITeacherService teacherService;

	@Autowired
	private UserRepository<User> UserRepository;

	@Autowired
	private IWriter writer;

	public User findUserByUsername(String username) {
		User userFound;

		userFound = UserRepository.findByEmail(username);

		userFound = studentService.findStudentByEmail(username);

		if (Verificator.isEmpty(userFound)) {
			userFound = findTeacher(username);
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

	@Override
	public List<String> enrollUserInClass(UserCourseIdPair userClassIdPair) {
		User userToBeEnrolled = userClassIdPair.getUser();
		List<String> enrolResult = new ArrayList<>();

		if (userToBeEnrolled instanceof Student) {
			StudentCoursePair studentCoursePair = new StudentCoursePair();
			studentCoursePair.setClassId(userClassIdPair.getCourseId());
			studentCoursePair.setStudentMail(userToBeEnrolled.getEmail());
			enrolResult = studentService.enlistStudentInCourse(studentCoursePair);
			return enrolResult;
		}

		if (userToBeEnrolled instanceof Teacher) {
			TeacherCoursePair teacherCoursePair = new TeacherCoursePair();
			teacherCoursePair.setCourseId(userClassIdPair.getCourseId());
			teacherCoursePair.setTeacherEmail(userToBeEnrolled.getEmail());
			enrolResult = teacherService.entollTeacherInCourse(teacherCoursePair);
			return enrolResult;
		} else {
			enrolResult.add("Enrolled user is invalid");
		}
		return enrolResult;
	}

}

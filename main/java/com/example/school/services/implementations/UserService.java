package com.example.school.services.implementations;

import com.example.school.utilities.mappers.StudentMapper;
import com.example.school.utilities.mappers.UserMapper;
import com.example.school.viewModels.Interfaces.UserViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.example.school.database.entities.User;
import com.example.school.exceptions.ValueException;
import com.example.school.repositories.UserRepository;
import com.example.school.services.interfaces.IStudentService;
import com.example.school.services.interfaces.ITeacherService;
import com.example.school.services.interfaces.IUserService;
import com.example.school.utilities.interfaces.IWriter;
import com.example.school.viewModels.ViewModelPairs.UserCourseIdPair;

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

	public UserViewModel findUserByUsername(String username) {
		User userFound;

		userFound = UserRepository.findByEmail(username);

		if (userFound == null) {
			return new UserViewModel();
		}

		return UserMapper.mapUserToViewModel(userFound);
	}

	@Override
	public List<String> enrollUserInClass(UserCourseIdPair userClassIdPair) {
		List<String> errors = new ArrayList<>();
		UserViewModel userToInsert = userClassIdPair.getUser();
		String courseId = userClassIdPair.getCourseId();
		/*User userToBeEnrolled = userClassIdPair.getUser();
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
		return enrolResult;*/
		return new ArrayList<>();
	}

}

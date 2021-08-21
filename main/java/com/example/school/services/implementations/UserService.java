package com.example.school.services.implementations;

import com.example.school.database.entities.Course;
import com.example.school.services.interfaces.ICourseService;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.utilities.mappers.CourseMapper;
import com.example.school.utilities.mappers.UserMapper;
import com.example.school.viewModels.Interfaces.UserViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.example.school.database.entities.User;
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
	private UserRepository<User> userRepository;

	@Autowired
	private ICourseService courseService;

	@Autowired
	private IWriter writer;

	public UserViewModel findUserByUsername(String username) {
		User userFound;

		userFound = userRepository.findByEmail(username);

		if (userFound == null) {
			return new UserViewModel();
		}

		return UserMapper.mapUserToViewModel(userFound);
	}

	@Override
	public ServiceReturnResult<UserViewModel> enrollUserInClass(UserViewModel user, Long courseId) {
		ServiceReturnResult<UserViewModel> returnResult = new ServiceReturnResult<>();
		ServiceReturnResult<Course> findCourseResult = courseService.getCourseById(courseId);
		User userToEnroll = userRepository.findByEmail(user.getEmail());

		if (findCourseResult.hasErrors()) {
			returnResult.addErrorMsg(findCourseResult.getErrorMessages());
		}

		Course foundCourse = findCourseResult.getReturnResultObject();
		userToEnroll.getCourses().add(foundCourse);
		user.getCourses().add(CourseMapper.mapEntityToCourseViewModel(foundCourse));
		returnResult.setReturnResultObject(user);
		userRepository.save(userToEnroll);

		return returnResult;
	}

}

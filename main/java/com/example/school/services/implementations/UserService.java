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
import com.example.school.utilities.UserEntityHelper;
import com.example.school.utilities.Verificator;
import com.example.school.utilities.interfaces.IWriter;
import com.example.school.viewModels.Interfaces.ViewModel;
import com.example.school.viewModels.decorators.ModelDecorator;
import com.example.school.viewModels.decorators.StudentVMValidator;
import com.example.school.viewModels.decorators.TeacherVMValidator;
import com.example.school.viewModels.decorators.VMValidator;

@Service
public class UserService implements IUserService{
	
	@Autowired
	private IStudentService studentService;
	
	@Autowired
	private ITeacherService teacherService;

	@Autowired
	private StudentFactory studentFactory;

	@Autowired
	private TeacherFactory teacherFactory;

	@Autowired
	private AuthGroupService authGroupService;
	
	@Autowired
	private IWriter writer;

	public ServiceReturnResult createUser(ViewModel viewModel) {
		User resultUser = new User();
		ServiceReturnResult userCreateResult = new ServiceReturnResult();
		boolean isStudent;
		isStudent = UserEntityHelper.isUserStudent(viewModel);
		VMValidator validator = isStudent ? new StudentVMValidator() : new TeacherVMValidator(); 
		
		ModelDecorator decorator = new ModelDecorator(viewModel);

		userCreateResult.addErrorMsg(decorator.validateModel(validator));

		if (!userCreateResult.isSuccessful()) {
			return userCreateResult;
		}

		ServiceReturnResult factoReturnResult = new ServiceReturnResult();

		factoReturnResult = isStudent ? studentFactory.getEntity(viewModel) : teacherFactory.getEntity(viewModel);

		if (!factoReturnResult.isSuccessful()) {
			return factoReturnResult;
		}

		resultUser = (User) factoReturnResult.getReturnResultObject();
		saveUser(resultUser); 

		authGroupService.addAuth(viewModel);

		userCreateResult.setReturnResultObject(resultUser);

		return userCreateResult;
	}

	private void saveUser(User user) {
		if (user instanceof Student) {
			studentService.createStudent((Student)user);
			return;
		}
		teacherService.addTeacher((Teacher) user);
	}
	
	public User findUserByUsername(String username) {
		User userFound;
		
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

}

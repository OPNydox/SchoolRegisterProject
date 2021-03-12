package com.example.school.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.school.database.entities.Course;
import com.example.school.database.entities.Student;
import com.example.school.exceptions.ValueException;
import com.example.school.factories.StudentFactory;
import com.example.school.repositories.StudentRepository;
import com.example.school.services.interfaces.ICourseService;
import com.example.school.services.interfaces.IStudentService;
import com.example.school.utilities.NumberHandler;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.utilities.StudentCoursePair;
import com.example.school.utilities.Verificator;
import com.example.school.utilities.interfaces.IWriter;
import com.example.school.utilities.mappers.StudentMapper;
import com.example.school.viewModels.StudentViewModel;
import com.example.school.viewModels.decorators.ModelDecorator;
import com.example.school.viewModels.decorators.StudentVMValidator;

@Service
public class StudentServiceImpl implements IStudentService {
	@Autowired
	private ICourseService courseService;

	@Autowired
	private AuthGroupService authGroupService;

	@Autowired
	private StudentRepository repository;

	@Autowired
	private StudentFactory studentFactory;

	@Autowired
	private IWriter writer;

	@Override
	public ServiceReturnResult<Student> createStudent(StudentViewModel student) {
		List<String> validationResult = new ArrayList<>();
		ServiceReturnResult<Student> studentCreateResult = new ServiceReturnResult<>();
		Student createdStudent;

		ModelDecorator decorator = new ModelDecorator(student);

		validationResult.addAll(decorator.validateModel(new StudentVMValidator()));

		if (!validationResult.isEmpty()) {
			studentCreateResult.addErrorMsg(validationResult);
			return studentCreateResult;
		}

		studentCreateResult = studentFactory.getEntity(student);

		createdStudent = repository.save(studentCreateResult.getReturnResultObject());
		studentCreateResult.setReturnResultObject(createdStudent);

		authGroupService.addAuth(student);

		return studentCreateResult;
	}

	@Override
	public Student findStudentByEmail(String email) {
		Student result = new Student();
		try {
			Verificator.isEmpty(email, "Email is empty");
			result = repository.findByEmail(email);
		} catch (ValueException e) {
			writer.writeError(e.getMessage());
			result.setEmpty();
		}
		return result;
	}

	@Override
	public List<Student> findAllStudents() {
		List<Student> result;

		result = (List<Student>) repository.findAll();
		return result;
	}

	@Override
	public List<String> enlistStudentInCourse(StudentCoursePair studentCoursePair) {
		List<String> result = new ArrayList<>();
		ServiceReturnResult<Course> courseReturnResult;
		Student studentToEnlist;
		Course courseToBeEnlisted;

		courseReturnResult = courseService.getCourseById(studentCoursePair.getCourseId());

		if (courseReturnResult.hasErrors()) {
			result.addAll(courseReturnResult.getErrorMessages());
			return result;
		}

		studentToEnlist = findStudentByEmail(studentCoursePair.getStundentEmail());
		courseToBeEnlisted = courseReturnResult.getReturnResultObject();

		studentToEnlist.getCourses().add(courseToBeEnlisted);

		updateStudent(studentToEnlist);

		return result;
	}

	private void updateStudent(Student studentToUpdate) {
		this.repository.save(studentToUpdate);
	}

	@Override
	public ServiceReturnResult<StudentViewModel> findStudentById(String id) {
		ServiceReturnResult<Student> findStudentResult;
		StudentViewModel studentViewModel;
		ServiceReturnResult<StudentViewModel> returnResult = new ServiceReturnResult<>();

		findStudentResult = findStudentEntityById(id);

		studentViewModel = StudentMapper.mapEntityTViewModel(findStudentResult.getReturnResultObject());
		returnResult.setReturnResultObject(studentViewModel);

		return returnResult;
	}

	@Override
	public ServiceReturnResult<Student> findStudentEntityById(String id) {
		Long longId;
		Optional<Student> foundStudentOptional;
		ServiceReturnResult<Long> convertLongResult;
		ServiceReturnResult<Student> returnResult = new ServiceReturnResult<>();
		
		convertLongResult = NumberHandler.parseStringToLong(id);

		if (convertLongResult.hasErrors()) {
			returnResult.addErrorMsg(convertLongResult.getErrorMessages());
			return returnResult;
		}

		longId = convertLongResult.getReturnResultObject();

		foundStudentOptional = repository.findById(longId);

		if (foundStudentOptional.isEmpty()) {
			returnResult.addErrorMsg("Student not found");
			return returnResult;
		}

		returnResult.setReturnResultObject(foundStudentOptional.get());
		
		return returnResult;
	}

	@Override
	public Student createStudent(Student student) {
		Student returnStudent = this.repository.save(student);
		return returnStudent;
	}

}

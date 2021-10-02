package com.example.school.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.school.repositories.UserRepository;
import com.example.school.viewModels.StudentProfileViewModel;
import com.example.school.viewModels.StudentRegistrationViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.school.database.entities.Course;
import com.example.school.database.entities.Student;
import com.example.school.exceptions.ValueException;
import com.example.school.factories.StudentFactory;
import com.example.school.services.interfaces.ICourseService;
import com.example.school.services.interfaces.IStudentService;
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
	private UserRepository userRepository;

	@Autowired
	private StudentFactory studentFactory;

	@Autowired
	private IWriter writer;

	@Override
	public ServiceReturnResult<Student> createStudent(StudentRegistrationViewModel student) {
		List<String> validationResult = new ArrayList<>();
		ServiceReturnResult<Student> studentCreateResult = new ServiceReturnResult<>();

		ModelDecorator decorator = new ModelDecorator(student);

		validationResult.addAll(decorator.validateModel(new StudentVMValidator()));

		if (!validationResult.isEmpty()) {
			studentCreateResult.addErrorMsg(validationResult);
			return studentCreateResult;
		}

		studentCreateResult = studentFactory.createStudentEntity(student);

		userRepository.save(studentCreateResult.getReturnResultObject());
		authGroupService.addAuth(student);

		return studentCreateResult;
	}

	@Override
	public Student findStudentByEmail(String email) {
		Student result = new Student();
		try {
			Verificator.isEmpty(email, "Email is empty");
			result = (Student) userRepository.findByEmail(email);
		} catch (ValueException e) {
			writer.writeError(e.getMessage());
			result.setEmpty();
		}
		return result;
	}

	@Override
	public List<Student> findAllStudents() {
		List<Student> result;

		result = (List<Student>) userRepository.findAll();
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

	@Override
	public StudentProfileViewModel getStudentProfileViewModel(StudentViewModel student) {
		StudentProfileViewModel result = new StudentProfileViewModel();
		result.setCourses(courseService.getAllCoursesForPerson(student.getEmail()));

		return result;
	}

	private void updateStudent(Student studentToUpdate) {
		this.userRepository.save(studentToUpdate);
	}

	@Override
	public ServiceReturnResult<StudentViewModel> findStudentById(Long id) {
		ServiceReturnResult<Student> findStudentResult;
		StudentViewModel studentViewModel;
		ServiceReturnResult<StudentViewModel> returnResult = new ServiceReturnResult<>();

		findStudentResult = findStudentEntityById(id);

		studentViewModel = StudentMapper.mapUserToViewModel(findStudentResult.getReturnResultObject());
		returnResult.setReturnResultObject(studentViewModel);

		return returnResult;
	}

	@Override
	public ServiceReturnResult<Student> findStudentEntityById(Long id) {
		Optional<Student> foundStudentOptional;
		ServiceReturnResult<Long> convertLongResult;
		ServiceReturnResult<Student> returnResult = new ServiceReturnResult<>();

		foundStudentOptional = userRepository.findById(id);

		if (foundStudentOptional.isEmpty()) {
			returnResult.addErrorMsg("Student not found");
			return returnResult;
		}

		returnResult.setReturnResultObject(foundStudentOptional.get());
		
		return returnResult;
	}

	@Override
	public Student createStudent(Student student) {
		Student returnStudent = (Student) this.userRepository.save(student);
		return returnStudent;
	}

}

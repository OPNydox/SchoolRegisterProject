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
import com.example.school.repositories.StudentRepository;
import com.example.school.services.interfaces.ICourseService;
import com.example.school.services.interfaces.IStudentService;
import com.example.school.servicesImplementations.CourseService;
import com.example.school.utilities.NumberHandler;
import com.example.school.utilities.PasswordManager;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.utilities.StudentCoursePair;
import com.example.school.utilities.Verificator;
import com.example.school.utilities.checkers.StudentChecker;
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
	private StudentRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private IWriter writer;

	private Student currentStudent;

	private Course currentCourse;

	private ServiceReturnResult returnResult;

	@Override
	public Student createStudent(StudentViewModel student) {
		Student newStudent = new Student();
		List<String> validationResult = new ArrayList<>();

		ModelDecorator decorator = new ModelDecorator(student);

		validationResult.addAll(decorator.validateModel(new StudentVMValidator()));

		if (!validationResult.isEmpty()) {
			newStudent.setEmpty();
			writer.writeErrors(validationResult);
			return newStudent;
		}

		newStudent.setEmail(student.getEmail());
		newStudent.setName(student.getName());
		newStudent.setPassword(passwordEncoder.encode(student.getPassword()));
		newStudent.setStudent(true);

		newStudent = repository.save(newStudent);

		return newStudent;
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
	public ServiceReturnResult enlistStudentInCourse(StudentCoursePair studentCoursePair) {
		ServiceReturnResult result = new ServiceReturnResult();
		ServiceReturnResult courseReturnResult;

		courseReturnResult = courseService.getCourseById(studentCoursePair.getCourseId());

		if (!courseReturnResult.isSuccessful()) {
			result.addErrorMsg(courseReturnResult.getErrorMessages());
			return result;
		}

		this.currentStudent = findStudentByEmail(studentCoursePair.getStundentEmail());
		this.currentCourse = (Course) courseReturnResult.getReturnResultObject();

		verifyStudentAndCourse(result, studentCoursePair);

		if (!result.isSuccessful()) {
			return result;
		}

		currentStudent.getCourses().add(this.currentCourse);

		updateStudent(currentStudent);

		return result;
	}

	public void verifyStudentAndCourse(ServiceReturnResult returnStatement, StudentCoursePair scp) {
		if (Verificator.isEmpty(this.currentStudent)) {
			returnStatement.addErrorMsg("Unable to find a student with email " + scp.getStundentEmail());
		}

		if (Verificator.isEmpty(this.currentCourse)) {
			returnStatement.addErrorMsg("Unable to find course with id " + scp.getCourseId());
		}

		checkIfstudentEnrolledInClass(returnStatement);
	}

	private void checkIfstudentEnrolledInClass(ServiceReturnResult returnStatement) {
		StudentChecker studentChecker;
		boolean classExists;

		studentChecker = new StudentChecker(this.currentStudent);

		classExists = studentChecker.checkIfStudentEnrolledInClass(this.currentCourse.getClassId());

		if (classExists) {
			returnStatement.addErrorMsg("Student already enerolled in this course");
		}

		return;
	}

	private void updateStudent(Student studentToUpdate) {
		this.repository.save(studentToUpdate);
	}

	@Override
	public ServiceReturnResult findStudentEntityById(String id) {
		Long longId;
		Optional<Student> foundStudentOptional;
		StudentViewModel studentViewModel;
		ServiceReturnResult convertLongResult;
		
		this.returnResult = new ServiceReturnResult();
		
		convertLongResult = NumberHandler.parseStringToLong(id);

		if (!convertLongResult.isSuccessful()) {
			return convertLongResult;
		}

		longId = (Long) convertLongResult.getReturnResultObject();

		foundStudentOptional = repository.findById(longId);

		if (foundStudentOptional.isEmpty()) {
			this.returnResult.addErrorMsg("Student not found");
			return returnResult;
		}

		this.returnResult.setReturnResultObject(foundStudentOptional.get());
		
		return this.returnResult;
	}

	@Override
	public ServiceReturnResult findStudentById(String id) {
		ServiceReturnResult studentResult;
		StudentViewModel studentViewModel;

		studentResult = findStudentEntityById(id);

		studentViewModel = StudentMapper.mapEntityTViewModel((Student)studentResult.getReturnResultObject());
		this.returnResult.setReturnResultObject(studentViewModel);

		return this.returnResult;
	}

}

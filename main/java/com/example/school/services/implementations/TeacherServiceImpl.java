package com.example.school.services.implementations;

import java.util.ArrayList;
import java.util.List;

import com.example.school.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.school.database.entities.Course;
import com.example.school.database.entities.Teacher;
import com.example.school.exceptions.EmailNotValidExcepiton;
import com.example.school.exceptions.ValueException;
import com.example.school.factories.TeacherFactory;
import com.example.school.repositories.CourseRepository;
import com.example.school.services.interfaces.ICourseService;
import com.example.school.services.interfaces.ITeacherService;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.utilities.Verificator;
import com.example.school.utilities.interfaces.IWriter;
import com.example.school.viewModels.TeacherViewModel;
import com.example.school.viewModels.ViewModelPairs.TeacherCoursePair;
import com.example.school.viewModels.decorators.ModelDecorator;
import com.example.school.viewModels.decorators.TeacherVMValidator;

@Service
public class TeacherServiceImpl implements ITeacherService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private TeacherFactory teacherFactory;

	@Autowired
	private ICourseService courseService;

	@Autowired
	private IWriter writer;

	@Override
	public ServiceReturnResult<TeacherViewModel> addTeacher(TeacherViewModel teacherView) {
		Teacher newTeacher = new Teacher();
		ServiceReturnResult<TeacherViewModel> addTeacherResult = new ServiceReturnResult<>();
		ServiceReturnResult<Teacher> teacherResult = new ServiceReturnResult<>();
		List<String> validationResult = new ArrayList<>();

		addTeacherResult.setReturnResultObject(teacherView);

		ModelDecorator decorator = new ModelDecorator(teacherView);

		validationResult.addAll(decorator.validateModel(new TeacherVMValidator()));

		if (!validationResult.isEmpty()) {
			addTeacherResult.addErrorMsg(validationResult);
			return addTeacherResult;
		}

		ServiceReturnResult<Teacher> factoryResult = new ServiceReturnResult<>();

		factoryResult = this.teacherFactory.getEntity(teacherView);

		if (factoryResult.hasErrors()) {
			addTeacherResult.addErrorMsg(factoryResult.getErrorMessages());
			return addTeacherResult;
		}

		newTeacher = factoryResult.getReturnResultObject();

		newTeacher = (Teacher) userRepository.save(newTeacher);

		teacherResult.setReturnResultObject(newTeacher);

		return addTeacherResult;
	}

	@Override
	public Teacher findTeacherByEmail(String email) throws ValueException {
		try {
			Verificator.isEmpty(email, "Teacher search email is empty");
		} catch (ValueException e) {
			writer.writeError(e.getMessage());
			return null;
		}

		boolean isEmailValid = Verificator.verifyEmail(email);

		if (isEmailValid) {
			throw new EmailNotValidExcepiton("The email: " + email + " is not a valid email");
		}

		Teacher result = (Teacher) userRepository.findByEmail(email);
		return result;
	}

	@Override
	public boolean addTeacherToCourse(Teacher teacher, Course course) {
		try {
			Verificator.isEmpty(teacher, "Teacher object is null");
			Verificator.isEmpty(course, "Course obect is empty");
		} catch (ValueException e) {
			writer.writeError(e.getMessage());
			return false;
		}

		teacher.getCourses().add(course);
		course.getTeachers().add(teacher);
		return true;
	}

	@Override
	public ServiceReturnResult<Void> addTeacherToCourse(String teacherEmail, String courseName) {
		Teacher teacher;
		Course course;
		ServiceReturnResult<Void> addTeacherResult = new ServiceReturnResult<>();

		try {
			Verificator.isEmpty(teacherEmail, "The teacher email string is empty");
			Verificator.isEmpty(courseName, "Course name string is empty");
		} catch (ValueException e) {
			addTeacherResult.addErrorMsg(e.getMessage());
			return addTeacherResult;
		}

		teacher = (Teacher) userRepository.findByEmail(teacherEmail);
		course = courseRepository.findByName(courseName);

		addTeacherToCourse(teacher, course);
		return addTeacherResult;
	}

	@Override
	public Teacher addTeacher(Teacher teacher) {
		Teacher returnTeacher = (Teacher) this.userRepository.save(teacher);
		return returnTeacher;
	}

	@Override
	public List<String> entollTeacherInCourse(TeacherCoursePair teacherCoursePair) {
		Teacher teacherToEnroll = new Teacher();
		Course targetCourse = new Course();
		ServiceReturnResult<Course> foundCourseResult = new ServiceReturnResult<>(); 
		List<String> enrollResult = new ArrayList<>();

		try {
			teacherToEnroll = findTeacherByEmail(teacherCoursePair.getTeacherEmail());
		} catch (ValueException exception) {
			enrollResult.add(exception.getMessage());
		}

		foundCourseResult = courseService.getCourseById(teacherCoursePair.getCourseId());
		
		if (foundCourseResult.hasErrors()) {
			foundCourseResult.getErrorMessages().forEach(messege -> 
				enrollResult.add(messege)
			);
			return enrollResult;
		}

		targetCourse = foundCourseResult.getReturnResultObject();
		teacherToEnroll.getCourses().add(targetCourse);
		targetCourse.getTeachers().add(teacherToEnroll);
		saveTeacher(teacherToEnroll);
		courseService.saveCourse(targetCourse);
		
		return enrollResult;
	}

	public void saveTeacher(Teacher teacher) {
		this.userRepository.save(teacher);
	}

}

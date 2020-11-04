package com.example.school.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.school.database.entities.Course;
import com.example.school.database.entities.Teacher;
import com.example.school.exceptions.EmailNotValidExcepiton;
import com.example.school.exceptions.EntityException;
import com.example.school.exceptions.ValueException;
import com.example.school.exceptions.ValueNotFoundException;
import com.example.school.factories.TeacherFactory;
import com.example.school.repositories.CourseRepository;
import com.example.school.repositories.TeacherRepository;
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
	private TeacherRepository teacherRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private TeacherFactory teacherFactory;

	@Autowired
	private ICourseService courseService;

	@Autowired
	private IWriter writer;

	@Override
	public ServiceReturnResult addTeacher(TeacherViewModel teacherView) {
		Teacher newTeacher = new Teacher();
		ServiceReturnResult teacherResult = new ServiceReturnResult();
		ServiceReturnResult validationResult = new ServiceReturnResult();

		ModelDecorator decorator = new ModelDecorator(teacherView);

		validationResult.addErrorMsg(decorator.validateModel(new TeacherVMValidator()));

		if (!validationResult.isSuccessful()) {
			writer.writeErrors(validationResult.getErrorMessages());
			newTeacher.setEmpty();
			return validationResult;
		}

		ServiceReturnResult factoryResult = new ServiceReturnResult();

		factoryResult = this.teacherFactory.getEntity(teacherView);

		if (!factoryResult.isSuccessful()) {
			return factoryResult;
		}

		newTeacher = (Teacher) factoryResult.getReturnResultObject();

		newTeacher = teacherRepository.save(newTeacher);

		teacherResult.setReturnResultObject(newTeacher);

		return teacherResult;
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

		Teacher result = teacherRepository.findByEmail(email);
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
	public boolean addTeacherToCourse(String teacherEmail, String courseName) {
		Teacher teacher;
		Course course;
		try {
			Verificator.isEmpty(teacherEmail, "The teacher email string is empty");
			Verificator.isEmpty(courseName, "Course name string is empty");
			teacher = teacherRepository.findByEmail(teacherEmail);
			Verificator.isEmpty(teacher, "Could not find teacher with email " + teacherEmail);
			course = courseRepository.findByName(courseName);
		} catch (ValueException e) {
			writer.writeError(e.getMessage());
			return false;
		}

		addTeacherToCourse(teacher, course);
		return true;
	}

	@Override
	public Teacher addTeacher(Teacher teacher) {
		Teacher returnTeacher = this.teacherRepository.save(teacher);
		return returnTeacher;
	}

	@Override
	public ServiceReturnResult entollTeacherInCourse(TeacherCoursePair teacherCoursePair) {
		Teacher teacherToEnroll = new Teacher();
		Course targetCourse = new Course();
		ServiceReturnResult foundCourseResult = new ServiceReturnResult(); 
		ServiceReturnResult enrollResult = new ServiceReturnResult();

		try {
			teacherToEnroll = findTeacherByEmail(teacherCoursePair.getTeacherEmail());
		} catch (ValueException exception) {
			enrollResult.addErrorMsg(exception.getMessage());
			
		}

		foundCourseResult = courseService.getCourseById(teacherCoursePair.getCourseId());
		
		if (!foundCourseResult.isSuccessful()) {
			return foundCourseResult;
		}

		try {
			targetCourse = (Course) foundCourseResult.getReturnResultObject();
			teacherToEnroll.getCourses().add(targetCourse);
			targetCourse.getTeachers().add(teacherToEnroll);
			saveTeacher(teacherToEnroll);
			courseService.saveCourse(targetCourse);
		} catch (Exception e) {
			enrollResult.addErrorMsg(e.getMessage());
		}
		
		return enrollResult;
	}

	public void saveTeacher(Teacher teacher) {
		this.teacherRepository.save(teacher);
	}

}

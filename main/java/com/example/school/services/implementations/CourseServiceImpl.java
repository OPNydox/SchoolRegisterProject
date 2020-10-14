package com.example.school.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.school.database.entities.Course;
import com.example.school.database.entities.User;
import com.example.school.exceptions.ValueException;
import com.example.school.repositories.CourseRepository;
import com.example.school.services.interfaces.ICourseService;
import com.example.school.services.interfaces.IStudentService;
import com.example.school.services.interfaces.ITeacherService;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.utilities.UserEntityHelper;
import com.example.school.utilities.Verificator;
import com.example.school.utilities.interfaces.IWriter;
import com.example.school.utilities.mappers.CourseMapper;
import com.example.school.viewModels.CourseViewModel;
import com.example.school.viewModels.decorators.CourseVMValidator;
import com.example.school.viewModels.decorators.ModelDecorator;

@Service
public class CourseServiceImpl implements ICourseService {

	@Autowired
	private CourseRepository repository;

	@Autowired
	private IWriter writer;

	@Autowired
	private UserService userService;

	private ServiceReturnResult currentResult = new ServiceReturnResult();

	@Override
	@Transactional
	public Course addCourse(final CourseViewModel course) {
		Course newCourse = new Course();
		final List<String> validationErrors = new ArrayList<>();

		final ModelDecorator decorator = new ModelDecorator(course);

		validationErrors.addAll(decorator.validateModel(new CourseVMValidator()));

		if (!validationErrors.isEmpty()) {
			newCourse.setEmpty();
			writer.writeErrors(validationErrors);
			return newCourse;
		}

		newCourse = new Course(course.getSubject(), course.getName(), Integer.parseInt(course.getHonorarium()));

		newCourse = repository.save(newCourse);
		return newCourse;
	}

	@Override
	public Course getCourseByName(final String courseName) {
		Course result = new Course();
		final CourseViewModel foundCourse = new CourseViewModel();
		try {
			Verificator.isEmpty(courseName, "Course name is empty");
			result = repository.findByName(courseName);
		} catch (final Exception e) {
			writer.writeError(e.getMessage());
			result.setEmpty();
		}
		return result;
	}

	@Override
	public void saveCourse(final Course course) {
		repository.save(course);

	}

	@Override
	public List<CourseViewModel> getAllCoursesForPerson(final String personEmail) {
		User foundUser;
		List<Course> listOfcourses;
		List<CourseViewModel> resultList = new ArrayList<>();

		foundUser = findUser(personEmail);

		if (Verificator.isEmpty(foundUser)) {
			return resultList;
		}

		listOfcourses = UserEntityHelper.getCoursesFromUser(foundUser);

		resultList = CourseMapper.mapEtityoToCourseViewModel(listOfcourses);

		return resultList;
	}

	private User findUser(final String userEmail) {
		User foundUser;

		foundUser = userService.findUserByUsername(userEmail);

		return foundUser;
	}

	@Override
	public List<CourseViewModel> getAllCourses() {
		List<CourseViewModel> resultList = new ArrayList<>();
		Iterable<Course> courses;

		courses = repository.findAll();
		resultList = CourseMapper.mapEtityoToCourseViewModel(courses);

		return resultList;
	}

	@Override
	public ServiceReturnResult getCourseVMById(String id) {
		Course course;
		CourseViewModel courseViewModel;

		this.currentResult = getCourseById(id);
		
		if (!currentResult.isSuccessful()) {
			return returnResult();
		}

		course = (Course) this.currentResult.getReturnResultObject();

		courseViewModel = CourseMapper.mapEtityoToCourseViewModel(course);

		attachObjectToResult(courseViewModel);

		return returnResult();
	}

	@Override
	public ServiceReturnResult getCourseById(String id) {
		Long courseId;
		Course foundCourse;

		checkForEmpty(id);

		if (!this.currentResult.isSuccessful()) {
			return returnResult();
		}

		courseId = parseStringToLong(id);

		foundCourse = getCourseFromRepo(courseId);

		attachObjectToResult(foundCourse);

		return returnResult();
	}

	private void checkForEmpty(String string) {
		if (string == null || string.isEmpty()) {
			this.currentResult.addErrorMsg("Input value is empty");
		}
	}

	private Long parseStringToLong(String string) {
		Long result = null;

		try {
			result = Long.parseLong(string);
		} catch (NumberFormatException e) {
			this.currentResult.addErrorMsg(e.getMessage());
		}

		return result;
	}

	private Course getCourseFromRepo(Long id) {
		Optional<Course> optionalCourse = repository.findById(id);
		Course result;

		if (!optionalCourse.isPresent()) {
			this.currentResult.addErrorMsg("Could not find a course with Id " + id.toString());
			return new Course();
		}

		result = optionalCourse.get();

		return result;
	}

	private void attachObjectToResult(Object course) {
		this.currentResult.setReturnResultObject(course);
		return;
	}

	private ServiceReturnResult returnResult() {
		ServiceReturnResult result = new ServiceReturnResult();
		try {
		 	result = this.currentResult.getCopy();
		} catch (CloneNotSupportedException e) {
			result.addErrorMsg(e.getMessage());
			return result;
		}

		clearService();
		return result;

	}

	private void clearService() {
		this.currentResult = null;
		this.currentResult = new ServiceReturnResult();
	}

}

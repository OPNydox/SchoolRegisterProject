package com.example.school.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.school.database.entities.Course;
import com.example.school.database.entities.User;
import com.example.school.factories.CourseFactory;
import com.example.school.repositories.CourseRepository;
import com.example.school.services.interfaces.ICourseService;
import com.example.school.utilities.NumberHandler;
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
	private UserService userService;

	@Autowired
	private IWriter writer;

	@Override
	@Transactional
	public ServiceReturnResult<Course> addCourse(CourseViewModel course) {
		CourseFactory courseFactory = new CourseFactory();
		ServiceReturnResult<Course> courseReturnResult = new ServiceReturnResult<>();
		ServiceReturnResult<Course> createResult;

		ModelDecorator decorator = new ModelDecorator(course);

		courseReturnResult.addErrorMsg(decorator.validateModel(new CourseVMValidator()));

		if (courseReturnResult.hasErrors()) {
			return courseReturnResult;
		}

		createResult = courseFactory.getEntity(course);

		if (createResult.hasErrors()) {
			courseReturnResult.addErrorMsg(createResult.getErrorMessages());
			return courseReturnResult;
		}

		repository.save((Course)createResult.getReturnResultObject());
		
		return courseReturnResult;
	}

	@Override
	public Course getCourseByName(final String courseName) {
		Course result = new Course();
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
	public ServiceReturnResult<CourseViewModel> getCourseVMById(String id) {
		Course course;
		CourseViewModel courseViewModel;
		ServiceReturnResult<CourseViewModel> result = new ServiceReturnResult<>();
		ServiceReturnResult<Course> courseFindResult = new ServiceReturnResult<>();

		courseFindResult = getCourseById(id);
		
		if (courseFindResult.hasErrors()) {
			result.addErrorMsg(courseFindResult.getErrorMessages());
		}

		course = courseFindResult.getReturnResultObject();
		courseViewModel = CourseMapper.mapEtityoToCourseViewModel(course);

		result.setReturnResultObject(courseViewModel);

		return result;
	}

	@Override
	public ServiceReturnResult<Course> getCourseById(String id) {
		Long courseId;
		Course foundCourse;
		ServiceReturnResult<Course> result  = new ServiceReturnResult<>();

		courseId = NumberHandler.parseStringToLong(id).getReturnResultObject();
		foundCourse = getCourseFromRepo(courseId);
		result.setReturnResultObject(foundCourse);

		return result;
	}

	private Course getCourseFromRepo(Long id) {
		Optional<Course> optionalCourse = repository.findById(id);
		Course result;

		if (!optionalCourse.isPresent()) {
			writer.writeError("Could not find a course with Id " + id.toString());
			return new Course();
		}

		result = optionalCourse.get();

		return result;
	}
}

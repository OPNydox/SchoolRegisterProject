package com.example.school.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import com.example.school.database.entities.Course;
import com.example.school.database.entities.Student;
import com.example.school.database.entities.Teacher;
import com.example.school.database.entities.User;
import com.example.school.exceptions.ValueException;
import com.example.school.repositories.UserRepository;
import com.example.school.services.interfaces.ICourseService;
import com.example.school.services.interfaces.IStudentCourseService;
import com.example.school.services.interfaces.IStudentService;
import com.example.school.services.interfaces.ITeacherCourseService;
import com.example.school.services.interfaces.ITeacherService;
import com.example.school.utilities.ControllerHelper;
import com.example.school.utilities.ReturnResult;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.utilities.mappers.TeacherMapper;
import com.example.school.viewModels.*;

@Controller
public class CourseController {
	
	@Autowired
	private ICourseService courseService;

	@Autowired
	private IStudentCourseService studentCourseService;

	@Autowired
	private ITeacherCourseService teacherCourseService;
	
	@GetMapping("/coursefind/{name}")
	public ReturnResult courseTesting(@PathVariable String name) {
		ReturnResult result = new ReturnResult();
		Course foundCourse;
		
		if (name == null || name.isEmpty()) {
			return ControllerHelper.returnError("Name not found");
		}
		
		foundCourse = courseService.getCourseByName(name);
		
		if (foundCourse == null || foundCourse.isNull()) {
			return ControllerHelper.returnError("Could not find course with name " + name);
		}
		
		result.setMessage("Course found");
		result.setSuccesful(true);
		result.getData().add(foundCourse);
		
		return result;
	}

	@GetMapping(value = "/courses")
	public String getAllCourses(Model model) {
		Iterable<CourseViewModel> courses;

		courses = courseService.getAllCourses();

		model.addAttribute("courses", courses);

		return "allCourses";
	}

	@GetMapping(value = "/course")
	public String getCoursePage(@RequestParam Long id ,Model model) {
		ServiceReturnResult<CourseViewModel> courseResult;

		courseResult = courseService.getCourseVMById(id);
		model.addAttribute("course", courseResult.getReturnResultObject());

		return "course";
	}
}

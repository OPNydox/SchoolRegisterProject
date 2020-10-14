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

import com.example.school.database.entities.Course;
import com.example.school.database.entities.Student;
import com.example.school.database.entities.Teacher;
import com.example.school.database.entities.User;
import com.example.school.exceptions.ValueException;
import com.example.school.repositories.StudentRepository;
import com.example.school.repositories.UserRepository;
import com.example.school.services.interfaces.ICourseService;
import com.example.school.services.interfaces.IStudentService;
import com.example.school.services.interfaces.ITeacherService;
import com.example.school.utilities.ControllerHelper;
import com.example.school.utilities.ReturnResult;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.viewModels.*;

@Controller
public class CourseController {
	
	@Autowired
	private ICourseService courseService;
	
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
	
	@PostMapping(value = "/coursecreate")
	public ReturnResult courseCreate(@RequestBody CourseViewModel course) {
		ReturnResult result = new ReturnResult();
		Course newCourse;
		
		if (course == null) {
			return ControllerHelper.returnError("Course is empty");
		}
		
		newCourse = courseService.addCourse(course);
		
		if (newCourse == null || newCourse.isNull()) {
			return ControllerHelper.returnError("Course could not be created");
		}
		
		result.setSuccesful(true);
		result.setMessage("Course created");
		result.getData().add(newCourse);
		
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
	public String getCoursePage(@RequestParam String id ,Model model) {
		CourseViewModel courseResult;
		ServiceReturnResult serviceResult;

		serviceResult = courseService.getCourseVMById(id);

		if (!serviceResult.isSuccessful()) {
			model.addAttribute("error", "Could not load course.");
			return "redirect:/error";
		}

		courseResult = (CourseViewModel) serviceResult.getReturnResultObject();

		model.addAttribute("course", courseResult);

		return "course";
	}
}

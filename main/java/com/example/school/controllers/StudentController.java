package com.example.school.controllers;

import com.example.school.services.interfaces.ICourseService;
import com.example.school.utilities.ControllerUtils;
import com.example.school.viewModels.CourseViewModel;
import com.example.school.viewModels.Interfaces.UserViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.school.database.entities.Student;
import com.example.school.services.interfaces.IStudentService;
import com.example.school.utilities.ControllerHelper;
import com.example.school.utilities.ReturnResult;
import com.example.school.viewModels.StudentViewModel;

import java.util.Set;

@Controller
@RequestMapping(value = "student")
public class StudentController {
	
	@Autowired
	private IStudentService studentService;

	@Autowired
	private ICourseService courseService;

	@GetMapping(value = "/profile")
	public String getStudentProfilePage(Model model) {
		UserViewModel userViewModel = ControllerUtils.getUserPrincipal().getUser();
		model.addAttribute("user", userViewModel);

		return "/studentProfile";

	}
	
	@PostMapping(value ="/studcreate", consumes="application/json", produces="application/json")
	public ReturnResult studentTesting(@RequestBody StudentViewModel studentVM ) {
		ReturnResult result = new ReturnResult();
		Student student = new Student();
		
		if (student == null || student.isNull()) {
			return ControllerHelper.returnError("Could not create student with such parameters");
		}
		
		result.setSuccesful(true);
		result.setMessage("Student created");
		result.getData().add(student);
		
		return result;
		
	}
	
	@GetMapping(value = "/find/{email}", consumes = "application/json", produces = "application/json")
	public ReturnResult studentFind(@PathVariable("email") String email) {
		ReturnResult result = new ReturnResult();
		Student student;
		
		if (email == null || email.isEmpty()) {
			return ControllerHelper.returnError("Email is empty");
		}
		
		student = studentService.findStudentByEmail(email);
		
		if (student == null || student.isNull()) {
			return ControllerHelper.returnError("Student with that email could not be found");
		}
		
		result.setMessage("Student found");
		result.setSuccesful(true);
		result.getData().addAll(student.getPresences());
		
		return result;
	}

}

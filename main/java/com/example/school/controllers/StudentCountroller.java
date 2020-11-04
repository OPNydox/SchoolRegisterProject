package com.example.school.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.View;

import com.example.school.authentication.MyUserPrincipal;
import com.example.school.database.entities.Student;
import com.example.school.services.interfaces.IStudentService;
import com.example.school.utilities.ControllerHelper;
import com.example.school.utilities.ReturnResult;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.utilities.StudentCoursePair;
import com.example.school.viewModels.StudentViewModel;

@Controller
public class StudentCountroller {
	
	@Autowired
	private IStudentService studentService;
	
	@PostMapping(value ="/studcreate", consumes="application/json", produces="application/json")
	public ReturnResult studentTesting(@RequestBody StudentViewModel studentVM ) {
		ReturnResult result = new ReturnResult();
		StudentViewModel model = new StudentViewModel();
		Student student = studentService.createStudent(studentVM);
		
		if (student == null || student.isNull()) {
			return ControllerHelper.returnError("Could not create student with such parameters");
		}
		
		result.setSuccesful(true);
		result.setMessage("Student created");
		result.getData().add(student);
		
		return result;
		
	}
	
	@GetMapping(value = "/studfind/{email}", consumes = "application/json", produces = "application/json")
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

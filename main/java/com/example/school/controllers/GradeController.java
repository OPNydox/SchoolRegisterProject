package com.example.school.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.school.database.entities.Course;
import com.example.school.database.entities.Grade;
import com.example.school.database.entities.Student;
import com.example.school.database.entities.User;
import com.example.school.services.interfaces.ICourseService;
import com.example.school.services.interfaces.IGradeService;
import com.example.school.services.interfaces.IStudentService;
import com.example.school.viewModels.GradeViewModel;

@Controller
public class GradeController {
	
	@Autowired
	private IGradeService gradeService;
	
	@Autowired
	private ICourseService courseService;
	
	@Autowired
	private IStudentService studentService;

	@PostMapping(value ="/gradecreate")
	public String studentTesting() {
		GradeViewModel grade = new GradeViewModel();
		grade.setClassName("10A");
		grade.setMark("4.5");
		grade.setStudentEmail("iivanov@abv.bg");
		gradeService.addGrade(grade);
		return "grade testig";
	}
}

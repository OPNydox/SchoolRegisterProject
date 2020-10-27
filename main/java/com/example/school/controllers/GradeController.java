package com.example.school.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

import com.example.school.database.entities.Course;
import com.example.school.database.entities.Grade;
import com.example.school.database.entities.Student;
import com.example.school.database.entities.User;
import com.example.school.services.interfaces.ICourseService;
import com.example.school.services.interfaces.IGradeService;
import com.example.school.services.interfaces.IStudentService;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.viewModels.CourseViewModel;
import com.example.school.viewModels.GradeViewModel;
import com.example.school.viewModels.StudentViewModel;

@Controller
public class GradeController {
	
	@Autowired
	private IGradeService gradeService;
	
	@Autowired
	private ICourseService courseService;
	
	@Autowired
	private IStudentService studentService;

	@PostMapping(value ="/gradecreate")
	public String studentTesting(@ModelAttribute GradeViewModel gradeViewModel, Model model) {
		ServiceReturnResult gradeReturnResult;

		gradeReturnResult = gradeService.addGrade(gradeViewModel);

		if (!gradeReturnResult.isSuccessful()) {
			model.addAttribute("errors", gradeReturnResult.getErrorMessages());
		}

		return "redirect:/profile";
	}

	@GetMapping(value = "/grade")
	public String getGradePage(@RequestParam String sid, @RequestParam String cid, Model model) {
		GradeViewModel formModel = new GradeViewModel();

		formModel.setCourseId(cid);
		formModel.setStudentId(sid);

		model.addAttribute("formModel", formModel);
		
		return "grade";
	}
}

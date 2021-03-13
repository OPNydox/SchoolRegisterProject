package com.example.school.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.school.database.entities.Teacher;
import com.example.school.exceptions.ValueException;
import com.example.school.services.interfaces.ITeacherService;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.viewModels.TeacherViewModel;

@Controller
@RequestMapping(value = "/teacher")
public class TeacherController {
	
	@Autowired
	private ITeacherService teacherService;

	@GetMapping("/create")
	public String getCreateTeacherPage(Model model) {
		if (!model.containsAttribute("teacherFormModel")) {
			model.addAttribute("teacherFormModel", new TeacherViewModel());
		}

		return "teacherCreate";
	}

	@PostMapping("/create")
	public String createTeacher(@ModelAttribute TeacherViewModel teacherViewModel, Model model) {
		ServiceReturnResult<TeacherViewModel> teacherCreateResult = new ServiceReturnResult<>();

		teacherCreateResult = teacherService.addTeacher(teacherViewModel);

		if (teacherCreateResult.hasErrors()) {
			model.addAttribute("teacherFormModel", teacherCreateResult);
			model.addAttribute("errors", teacherCreateResult.getErrorMessages());
			return "teacherCreate";
		}

		return "redirect:/profile";
	}
	
	@RequestMapping(value = "techtest")
	public String teacherTesting() {
		TeacherViewModel teacher = new TeacherViewModel("Dimitar Dimitrov", "ddmitrov@abv.bg", "1234", "12.3");
		teacherService.addTeacher(teacher);
		Teacher teacherFound = null;
		try {
			teacherFound = teacherService.findTeacherByEmail("ddmitrov@abv.bg");
		} catch (ValueException e) {
			System.out.println(e.getMessage());
			return "teacher test";
		}
		
		if (teacherFound == null) {
			System.out.println("Teacher could not be found");
		}
		System.out.println("The teacher has been found");
		System.out.println("His name is " + teacherFound.getName());
		return "teacher test";
	}
}

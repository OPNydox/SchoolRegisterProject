package com.example.school.controllers;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

import com.example.school.authentication.MyUserPrincipal;
import com.example.school.services.interfaces.IStudentService;
import com.example.school.services.interfaces.IUserService;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.utilities.StudentCoursePair;
import com.example.school.viewModels.Interfaces.UserViewModel;
import com.example.school.viewModels.ViewModelPairs.UserCourseIdPair;
import com.mysql.cj.protocol.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EnrollController {

    @Autowired
    private IStudentService studentService;

    @Autowired
    private IUserService userService;
    
    @GetMapping(value = "/enroll")
	public String enrollStudent(@RequestParam("cid") Long cid,
	 							Principal principal,
	 							Model model) {
		UserCourseIdPair studentCoursePair = new UserCourseIdPair();
		ServiceReturnResult<UserViewModel> enlistResult;
		List<String> errors = new LinkedList<>();
		Long classId = cid;
		
		MyUserPrincipal myUserPrincipal = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		enlistResult = userService.enrollUserInClass(myUserPrincipal.getUser(), classId);

		errors.addAll(enlistResult.getErrorMessages());

		model.addAttribute("errors", errors);

		return "redirect:/profile";
	}
}

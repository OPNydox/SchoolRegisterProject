package com.example.school.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.school.authentication.MyUserDetailsService;
import com.example.school.authentication.MyUserPrincipal;
import com.example.school.database.entities.Student;
import com.example.school.database.entities.Teacher;
import com.example.school.database.entities.User;
import com.example.school.services.interfaces.ICourseService;
import com.example.school.servicesImplementations.CourseService;
import com.example.school.utilities.mappers.CourseMapper;
import com.example.school.viewModels.CourseViewModel;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {
	
	@Autowired
	private ICourseService courseService;

	@GetMapping(value = "/")
	public String getHomePage() {
		return "redirect:profile";
	}

	@GetMapping(value = "/profile")
	public String getProfilePage(Principal principal, Model model) {
		String userEmail = principal.getName();
		List<CourseViewModel> userCourses = new ArrayList<>();

		userCourses = courseService.getAllCoursesForPerson(userEmail);
		model.addAttribute("userCourses", userCourses);
		
		return "profile";
	}

	@GetMapping(value = "/logout")
	public String logoutUser(HttpServletRequest request, HttpServletResponse response) {
		org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}

		return "redirect:/profile";
	}
}

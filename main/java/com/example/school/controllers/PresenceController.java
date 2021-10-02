package com.example.school.controllers;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.school.database.entities.Presence;
import com.example.school.services.interfaces.ICourseService;
import com.example.school.services.interfaces.IPresenceService;
import com.example.school.services.interfaces.IStudentService;
import com.example.school.utilities.ControllerHelper;
import com.example.school.utilities.ReturnResult;
import com.example.school.viewModels.PresenceViewModel;

@Controller
public class PresenceController {
	
	@Autowired
	private IPresenceService presenceService;
	
	@Autowired
	private ICourseService courseService;
	
	@Autowired
	private IStudentService studentService;
	
	@PostMapping(value = "/createpresence", produces="application/json", consumes="application/json")
	public ReturnResult createPresence(@RequestBody PresenceViewModel presence) {
		ReturnResult result = new ReturnResult();
		Presence newPresence;
		if (presence == null) {
			return ControllerHelper.returnError("Presence data could not be found");
		}
		
		newPresence = presenceService.addPresence(presence);
		
		if (newPresence == null || newPresence.isNull()) {
			return ControllerHelper.returnError("Presence could not be created");
		}
		
		result.setSuccesful(true);
		result.setMessage("New presence created");
		result.getData().add(newPresence);
		return result;
	}
	
	@GetMapping("/getpresencesstudent")
	public ReturnResult getPresencesStudent(@RequestParam("email") String email) {
		List<Presence> presences;
		ReturnResult result = new ReturnResult();

		if (email == null || email.isEmpty()) {
			return ControllerHelper.returnError("Requested email is empty");
		}
		
		presences = presenceService.getPresenceForStudentEmail(email);
		
		if (presences.isEmpty()) {
			return ControllerHelper.returnError("No presences found for this student");
		}
		
		result.setSuccesful(true);
		result.setMessage("Foud some presences for student with email " + email);
		result.getData().addAll(presences);
		
		return result;
	}
	
	@GetMapping("/getpresencesclass")
	public ReturnResult getPresencesClass(@RequestParam("name") String courseName) {
		List<Presence> presences;
		ReturnResult result = new ReturnResult();
		
		if (courseName == null || courseName.isEmpty()) {
			return ControllerHelper.returnError("Course name not found");
		}
		
		presences = presenceService.getPresencesForClassName(courseName);
		
		if (presences.isEmpty()) {
			return ControllerHelper.returnError("No presences found for this course");
		}
		
		result.setSuccesful(true);
		result.setMessage("Presences found");
		result.getData().addAll(presences);
		
		return result;
	}

	@GetMapping("/add/presence")
	public String addPresence(@RequestParam Long sid, @RequestParam Long cid, Model model) {
		presenceService.addPresence(sid, cid);
		String redirectString = "redirect:/course?id=" + cid;
		return redirectString;
	}

}

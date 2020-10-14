package com.example.school.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.school.services.interfaces.IEmailService;

@Controller
public class EmailController {
	@Autowired
	private IEmailService eService;
	
	@GetMapping(value = "/sendmail")
	public void sendEmail() {
		eService.sendEmail();
	}
}

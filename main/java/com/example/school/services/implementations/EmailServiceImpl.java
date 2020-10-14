package com.example.school.services.implementations;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.school.services.interfaces.IEmailService;
import com.example.school.utilities.emaiModule.interfaces.IEmailSender;

@Service
public class EmailServiceImpl implements IEmailService{

	@Autowired
	private IEmailSender emailSender;
	
	@Override
	public void sendEmail() {
		try {
			emailSender.sendEmail();
		} catch (MessagingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

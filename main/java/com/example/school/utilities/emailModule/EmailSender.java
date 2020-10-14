package com.example.school.utilities.emailModule;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Component;

import com.example.school.utilities.emaiModule.interfaces.IEmailSender;

@Component
public class EmailSender implements IEmailSender {

	@Override
	public void sendEmail() throws AddressException, MessagingException, IOException{
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication("sendemailtest9@gmail.com", "NoH*`!!3h=DD");
		      }
		   });
		   Message msg = new MimeMessage(session);
		   msg.setFrom(new InternetAddress("cbotushanov@gmail.com", false));

		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("cbotushanov@gmail.com"));
		   msg.setSubject("students");
		   msg.setContent("Student limit reached", "text/html");
		   msg.setSentDate(new Date());

		   //MimeBodyPart messageBodyPart = new MimeBodyPart();
		   //messageBodyPart.setContent("Tutorials point email", "text/html");

		   //Multipart multipart = new MimeMultipart();
		   //multipart.addBodyPart(messageBodyPart);

		   //msg.setContent(multipart);
		   Transport.send(msg); 
	}

}

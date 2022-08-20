package com.isa.project.service.implementation;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.isa.project.model.User;
import com.isa.project.service.EmailService;

@Service
public class EmailServiceImplementation implements EmailService{

	@Autowired
    private JavaMailSender javaMailSender;
	
	@Autowired
	private Environment env;
	
	@Override
	@Async
	public void sendEmail(User user) throws MessagingException  {
		
		MimeMessage message=javaMailSender.createMimeMessage();
	    MimeMessageHelper helper;
	    helper=new MimeMessageHelper(message,true);
	    helper.setTo(new InternetAddress(user.getUsername()));
	    helper.setFrom(new InternetAddress(env.getProperty("spring.mail.username")));
	    helper.setSubject("Account activation");
	    helper.setText("Hello " + user.getName() + " " + user.getSurname() +  ",\n\nPlease click the link below to verify your account" + "\n\nhttp://localhost:8080/api/users/activate/"+ user.getUsername() + "\n\nThank you!");
	    javaMailSender.send(message);
		         
		
	}

	@Override
	public void acceptEmail(User user) throws MessagingException {
		
		MimeMessage message=javaMailSender.createMimeMessage();
	    MimeMessageHelper helper;
	    helper=new MimeMessageHelper(message,true);
	    helper.setTo(new InternetAddress(user.getUsername()));
	    helper.setFrom(new InternetAddress(env.getProperty("spring.mail.username")));
	    helper.setSubject("Account activation");
	    helper.setText("Hello " + user.getName() + " " + user.getSurname() +  ",\n\nYour registration has been accepted!" + "\n\nThank you for registering!");
	    javaMailSender.send(message);
		
	}

	@Override
	public void declineEmail(User user, String reason) throws MessagingException {
		// TODO Auto-generated method stub
		MimeMessage message=javaMailSender.createMimeMessage();
	    MimeMessageHelper helper;
	    helper=new MimeMessageHelper(message,true);
	    helper.setTo(new InternetAddress(user.getUsername()));
	    helper.setFrom(new InternetAddress(env.getProperty("spring.mail.username")));
	    helper.setSubject("Account activation");
	    helper.setText("Hello " + user.getName() + " " + user.getSurname() +  ",\n\nYour registration has been declined because " + reason + "." + "\n\nThank you for registering!");
	    javaMailSender.send(message);
	}

	@Override
	public void acceptDeletingAccountEmail(User user, String reason) throws MessagingException {
		MimeMessage message=javaMailSender.createMimeMessage();
	    MimeMessageHelper helper;
	    helper=new MimeMessageHelper(message,true);
	    helper.setTo(new InternetAddress(user.getUsername()));
	    helper.setFrom(new InternetAddress(env.getProperty("spring.mail.username")));
	    helper.setSubject("Account activation");
	    helper.setText("Hello " + user.getName() + " " + user.getSurname() +  ",\n\nYour account has been deleted successfully because " + reason + "." + "\n\n");
	    javaMailSender.send(message);
		
	}

	@Override
	public void declineDeletingAccountEmail(User user, String reason) throws MessagingException {
		MimeMessage message=javaMailSender.createMimeMessage();
	    MimeMessageHelper helper;
	    helper=new MimeMessageHelper(message,true);
	    helper.setTo(new InternetAddress(user.getUsername()));
	    helper.setFrom(new InternetAddress(env.getProperty("spring.mail.username")));
	    helper.setSubject("Account activation");
	    helper.setText("Hello " + user.getName() + " " + user.getSurname() +  ",\n\nYour request for deleting account is declined because " + reason + "." + "\n\n");
	    javaMailSender.send(message);
		
	}

}

package com.isa.project.service;

import javax.mail.MessagingException;

import com.isa.project.model.User;

public interface EmailService {
	
	public void sendEmail(User user) throws MessagingException;
}

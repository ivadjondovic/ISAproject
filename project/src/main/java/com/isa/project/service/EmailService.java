package com.isa.project.service;

import javax.mail.MessagingException;

import com.isa.project.model.User;

public interface EmailService {
	
	public void sendEmail(User user) throws MessagingException;
	public void acceptEmail(User user) throws MessagingException;
	public void declineEmail(User user, String reason) throws MessagingException;
	public void acceptDeletingAccountEmail(User user, String reason) throws MessagingException;
	public void declineDeletingAccountEmail(User user, String reason) throws MessagingException;
}

package com.isa.project.service;

import javax.mail.MessagingException;

import com.isa.project.dto.ComplaintResponseDTO;
import com.isa.project.dto.RevisionResponseDTO;
import com.isa.project.model.BoatReservation;
import com.isa.project.model.ClientReview;
import com.isa.project.model.CottageReservation;
import com.isa.project.model.FishingLessonReservation;
import com.isa.project.model.QuickBoatReservation;
import com.isa.project.model.QuickCottageReservation;
import com.isa.project.model.QuickFishingLessonReservation;
import com.isa.project.model.User;

public interface EmailService {
	
	public void sendEmail(User user) throws MessagingException;
	public void acceptEmail(User user) throws MessagingException;
	public void declineEmail(User user, String reason) throws MessagingException;
	public void acceptDeletingAccountEmail(User user, String reason) throws MessagingException;
	public void declineDeletingAccountEmail(User user, String reason) throws MessagingException;
	public void sendCottageReservationMail(User user, CottageReservation cottageReservation) throws MessagingException;
	public void sendQuickCottageReservationMail(User user, QuickCottageReservation quickCottageReservation) throws MessagingException;
	public void sendBoatReservationMail(User user, BoatReservation boatReservation) throws MessagingException;
	public void sendQuickBoatReservationMail(User user, QuickBoatReservation quickBoatReservation) throws MessagingException;
	public void sendFishingLessonReservationMail(User user, FishingLessonReservation fishingLessonReservation) throws MessagingException;
	public void sendQuickFishingLessonReservationMail(User user, QuickFishingLessonReservation quickFishingLessonReservation) throws MessagingException;
	public void approveRevisionEmail(User user,RevisionResponseDTO revision) throws MessagingException;
	public void complaintOwnerEmail(User user, ComplaintResponseDTO dto, String answer) throws MessagingException;
	public void complaintClientEmail(User user, ComplaintResponseDTO dto, String answer) throws MessagingException;
	public void acceptPenaltyClientEmail(User user, ClientReview review) throws MessagingException;
	public void declinePenaltyClientEmail(User user, ClientReview review) throws MessagingException;
	public void acceptPenaltyInstructorEmail(User user, ClientReview review) throws MessagingException;
	public void declinePenaltyInstructorEmail(User user, ClientReview review) throws MessagingException;
}

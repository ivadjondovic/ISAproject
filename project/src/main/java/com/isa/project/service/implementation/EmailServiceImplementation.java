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

import com.isa.project.dto.ComplaintResponseDTO;
import com.isa.project.dto.RevisionResponseDTO;
import com.isa.project.model.BoatReservation;
import com.isa.project.model.CottageReservation;
import com.isa.project.model.FishingLessonReservation;
import com.isa.project.model.QuickBoatReservation;
import com.isa.project.model.QuickCottageReservation;
import com.isa.project.model.QuickFishingLessonReservation;
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

	@Override
	@Async
	public void sendCottageReservationMail(User user, CottageReservation cottageReservation) throws MessagingException {
		MimeMessage message=javaMailSender.createMimeMessage();
	    MimeMessageHelper helper;
	    helper=new MimeMessageHelper(message,true);
	    helper.setTo(new InternetAddress(user.getUsername()));
	    helper.setFrom(new InternetAddress(env.getProperty("spring.mail.username")));
	    helper.setSubject("Cottage reservation");
	    helper.setText("Hello " + user.getName() + " " + user.getSurname() + "\n\nYour cottage reservation:" + "\n\nCottage: " + cottageReservation.getCottage().getName() + "\n\nFrom-To: " + cottageReservation.getStartDate() + "-" + cottageReservation.getEndDate() + "\n\nPrice: " + cottageReservation.getPrice()  +  ",\n\nPlease click the link below to confirm your cottage reservation" + "\n\nhttp://localhost:8080/api/cottageReservations/accept/"+ cottageReservation.getId() + "\n\nThank you!");
	    javaMailSender.send(message);
		
	}

	@Override
	@Async
	public void sendQuickCottageReservationMail(User user, QuickCottageReservation quickCottageReservation)
			throws MessagingException {
		MimeMessage message=javaMailSender.createMimeMessage();
	    MimeMessageHelper helper;
	    helper=new MimeMessageHelper(message,true);
	    helper.setTo(new InternetAddress(user.getUsername()));
	    helper.setFrom(new InternetAddress(env.getProperty("spring.mail.username")));
	    helper.setSubject("Quick cottage reservation");
	    helper.setText("Hello " + user.getName() + " " + user.getSurname() + "\n\nYour cottage reservation:" + "\n\nCottage: " + quickCottageReservation.getCottage().getName() + "\n\nFrom-To: " + quickCottageReservation.getStartDate() + "-" + quickCottageReservation.getEndDate() + "\n\nPrice: " + quickCottageReservation.getPrice()  +  ",\n\nPlease click the link below to confirm your quick cottage reservation" + "\n\nhttp://localhost:8080/api/quickCottageReservations/accept/"+ quickCottageReservation.getId() + "\n\nThank you!");
	    javaMailSender.send(message);
		
	}

	@Override
	@Async
	public void sendBoatReservationMail(User user, BoatReservation boatReservation) throws MessagingException {
		MimeMessage message=javaMailSender.createMimeMessage();
	    MimeMessageHelper helper;
	    helper=new MimeMessageHelper(message,true);
	    helper.setTo(new InternetAddress(user.getUsername()));
	    helper.setFrom(new InternetAddress(env.getProperty("spring.mail.username")));
	    helper.setSubject("Boat reservation");
	    helper.setText("Hello " + user.getName() + " " + user.getSurname() + "\n\nYour boat reservation:" + "\n\nBoat: " + boatReservation.getBoat().getName() + "\n\nFrom-To: " + boatReservation.getStartDate() + "-" + boatReservation.getEndDate() + "\n\nPrice: " + boatReservation.getPrice()  +  ",\n\nPlease click the link below to confirm your boat reservation" + "\n\nhttp://localhost:8080/api/boatReservations/accept/"+ boatReservation.getId() + "\n\nThank you!");
	    javaMailSender.send(message);
		
	}

	@Override
	@Async
	public void sendQuickBoatReservationMail(User user, QuickBoatReservation quickBoatReservation)
			throws MessagingException {
		MimeMessage message=javaMailSender.createMimeMessage();
	    MimeMessageHelper helper;
	    helper=new MimeMessageHelper(message,true);
	    helper.setTo(new InternetAddress(user.getUsername()));
	    helper.setFrom(new InternetAddress(env.getProperty("spring.mail.username")));
	    helper.setSubject("Quick boat reservation");
	    helper.setText("Hello " + user.getName() + " " + user.getSurname() + "\n\nYour boat reservation:" + "\n\nBoat: " + quickBoatReservation.getBoat().getName() + "\n\nFrom-To: " + quickBoatReservation.getStartDate() + "-" + quickBoatReservation.getEndDate() + "\n\nPrice: " + quickBoatReservation.getPrice()  +  ",\n\nPlease click the link below to confirm your quick boat reservation" + "\n\nhttp://localhost:8080/api/quickBoatReservations/accept/"+ quickBoatReservation.getId() + "\n\nThank you!");
	    javaMailSender.send(message);
		
	}

	@Override
	@Async
	public void sendFishingLessonReservationMail(User user, FishingLessonReservation fishingLessonReservation)
			throws MessagingException {
		MimeMessage message=javaMailSender.createMimeMessage();
	    MimeMessageHelper helper;
	    helper=new MimeMessageHelper(message,true);
	    helper.setTo(new InternetAddress(user.getUsername()));
	    helper.setFrom(new InternetAddress(env.getProperty("spring.mail.username")));
	    helper.setSubject("Fishing lesson reservation");
	    helper.setText("Hello " + user.getName() + " " + user.getSurname() + "\n\nYour fishing lesson reservation:" + "\n\nFishing lesson: " + fishingLessonReservation.getFishingLesson().getName() + "\n\nFrom-To: " + fishingLessonReservation.getStartDate() + "-" + fishingLessonReservation.getEndDate() + "\n\nPrice: " + fishingLessonReservation.getPrice()  +  ",\n\nPlease click the link below to confirm your fishing lesson reservation" + "\n\nhttp://localhost:8080/api/fishingLessonReservations/accept/"+ fishingLessonReservation.getId() + "\n\nThank you!");
	    javaMailSender.send(message);
		
	}

	@Override
	@Async
	public void sendQuickFishingLessonReservationMail(User user,
			QuickFishingLessonReservation quickFishingLessonReservation) throws MessagingException {
		MimeMessage message=javaMailSender.createMimeMessage();
	    MimeMessageHelper helper;
	    helper=new MimeMessageHelper(message,true);
	    helper.setTo(new InternetAddress(user.getUsername()));
	    helper.setFrom(new InternetAddress(env.getProperty("spring.mail.username")));
	    helper.setSubject("Quick fishing lesson reservation");
	    helper.setText("Hello " + user.getName() + " " + user.getSurname() + "\n\nYour fishing lesson reservation:" + "\n\nFishing lesson: " + quickFishingLessonReservation.getFishingLesson().getName() + "\n\nFrom-To: " + quickFishingLessonReservation.getStartDate() + "-" + quickFishingLessonReservation.getEndDate() + "\n\nPrice: " + quickFishingLessonReservation.getPrice()  +  ",\n\nPlease click the link below to confirm your quick fishing lesson reservation" + "\n\nhttp://localhost:8080/api/quickFishingLessonReservations/accept/"+ quickFishingLessonReservation.getId() + "\n\nThank you!");
	    javaMailSender.send(message);
		
	}

	@Async
	@Override
	public void approveRevisionEmail(User user, RevisionResponseDTO revision) throws MessagingException {
		MimeMessage message=javaMailSender.createMimeMessage();
	    MimeMessageHelper helper;
	    helper=new MimeMessageHelper(message,true);
	    helper.setTo(new InternetAddress(user.getUsername()));
	    helper.setFrom(new InternetAddress(env.getProperty("spring.mail.username")));
	    helper.setSubject("Revision");
	    helper.setText("Hello " + user.getName() + " " + user.getSurname() +  ",\n\nWe are sending you a client revision for your entity." + "\n\nEntity: " + revision.getEntityName() + "\nEntity rate: " + revision.getEntityRate() + "\nEntity owner rate: " + revision.getOwnerRate() + "\nRevision: " + revision.getDescription());
	    javaMailSender.send(message);
		
	}

	@Async
	@Override
	public void complaintOwnerEmail(User user, ComplaintResponseDTO dto, String answer) throws MessagingException {
		MimeMessage message=javaMailSender.createMimeMessage();
	    MimeMessageHelper helper;
	    helper=new MimeMessageHelper(message,true);
	    helper.setTo(new InternetAddress(user.getUsername()));
	    helper.setFrom(new InternetAddress(env.getProperty("spring.mail.username")));
	    helper.setSubject("Complaint");
	    helper.setText("Hello " + user.getName() + " " + user.getSurname() +  ",\n\nWe are sending you a client complaint for your entity." + "\n\n" + dto.getComplaintType() + ": " + dto.getEntityName()  + "\nComplaint: " + dto.getDescription() + "\n\nAdmin answer: " + answer);
	    javaMailSender.send(message);
		
	}

	@Async
	@Override
	public void complaintClientEmail(User user, ComplaintResponseDTO dto, String answer) throws MessagingException {
		MimeMessage message=javaMailSender.createMimeMessage();
	    MimeMessageHelper helper;
	    helper=new MimeMessageHelper(message,true);
	    helper.setTo(new InternetAddress(user.getUsername()));
	    helper.setFrom(new InternetAddress(env.getProperty("spring.mail.username")));
	    helper.setSubject("Complaint");
	    helper.setText("Hello " + user.getName() + " " + user.getSurname() +  ",\n\nWe are sending admin answer for your complaint about "  + dto.getComplaintType() + ": " + dto.getEntityName()  + "\nYour complaint: " + dto.getDescription() + "\n\nAdmin answer: " + answer);
	    javaMailSender.send(message);
		
	}

	

}

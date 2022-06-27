package net.javaguides.isa.service.implementation;

import net.javaguides.isa.config.EmailContext;
import net.javaguides.isa.model.BoatOwner;
import net.javaguides.isa.model.CottageOwner;
import net.javaguides.isa.model.FishingInstructor;
import net.javaguides.isa.service.IEmailService;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

@Service
public class EmailService implements IEmailService {

    private final EmailContext _emailContext;

    public EmailService(EmailContext emailContext) {
        _emailContext = emailContext;
    }

    @Override
    public void approveBoatOwnerRegistrationMail(BoatOwner boatOwner) {
        String to = boatOwner.getUser().getUsername();
        String subject = "Your registration has been approved.";
        Context context = new Context();
        context.setVariable("name", String.format("%s %s", boatOwner.getFirstName(), boatOwner.getLastName()));
        context.setVariable("link", String.format("http://localhost:4200/frontpage/login/%s", boatOwner.getId()));
        _emailContext.send(to, subject, "approvedRegistration", context);
    }

    @Override
    public void denyBoatOwnerRegistrationMail(BoatOwner boatOwner, String reason) {
        String to = boatOwner.getUser().getUsername();
        String subject = "Your registration has been denied.";
        Context context = new Context();
        context.setVariable("name", String.format("%s %s", boatOwner.getFirstName(), boatOwner.getLastName()));
        context.setVariable("reason", String.format("%s", reason));
        _emailContext.send(to, subject, "deniedRegistration", context);
    }

    @Override
    public void approveCottageOwnerRegistrationMail(CottageOwner cottageOwner) {
        String to = cottageOwner.getUser().getUsername();
        String subject = "Your registration has been approved.";
        Context context = new Context();
        context.setVariable("name", String.format("%s %s", cottageOwner.getFirstName(), cottageOwner.getLastName()));
        context.setVariable("link", String.format("http://localhost:4200/frontpage/login/%s", cottageOwner.getId()));
        _emailContext.send(to, subject, "approvedRegistration", context);
    }

    @Override
    public void denyCottageOwnerRegistrationMail(CottageOwner cottageOwner, String reason) {
        String to = cottageOwner.getUser().getUsername();
        String subject = "Your registration has been denied.";
        Context context = new Context();
        context.setVariable("name", String.format("%s %s", cottageOwner.getFirstName(), cottageOwner.getLastName()));
        context.setVariable("reason", String.format("%s", reason));
        _emailContext.send(to, subject, "deniedRegistration", context);
    }

    @Override
    public void approveFishingInstructorRegistrationMail(FishingInstructor fishingInstructor) {
        String to = fishingInstructor.getUser().getUsername();
        String subject = "Your registration has been approved.";
        Context context = new Context();
        context.setVariable("name", String.format("%s %s", fishingInstructor.getFirstName(), fishingInstructor.getLastName()));
        context.setVariable("link", String.format("http://localhost:4200/frontpage/login/%s", fishingInstructor.getId()));
        _emailContext.send(to, subject, "approvedRegistration", context);
    }

    @Override
    public void denyFishingInstructorRegistrationMail(FishingInstructor fishingInstructor, String reason) {
        String to = fishingInstructor.getUser().getUsername();
        String subject = "Your registration has been denied.";
        Context context = new Context();
        context.setVariable("name", String.format("%s %s", fishingInstructor.getFirstName(), fishingInstructor.getLastName()));
        context.setVariable("reason", String.format("%s", reason));
        _emailContext.send(to, subject, "deniedRegistration", context);
    }
}

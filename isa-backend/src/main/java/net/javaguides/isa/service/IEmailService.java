package net.javaguides.isa.service;

import net.javaguides.isa.model.BoatOwner;
import net.javaguides.isa.model.CottageOwner;
import net.javaguides.isa.model.FishingInstructor;
import net.javaguides.isa.model.User;

public interface IEmailService {
    void approveBoatOwnerRegistrationMail(BoatOwner boatOwner);
    void denyBoatOwnerRegistrationMail(BoatOwner boatOwner, String reason);
    void approveCottageOwnerRegistrationMail(CottageOwner cottageOwner);
    void denyCottageOwnerRegistrationMail(CottageOwner cottageOwner, String reason);
    void approveFishingInstructorRegistrationMail(FishingInstructor fishingInstructor);
    void denyFishingInstructorRegistrationMail(FishingInstructor fishingInstructor, String reason);
    void approveDeletionRequest(User user, String reason);
    void denyDeletionRequest(User user, String reason);
}
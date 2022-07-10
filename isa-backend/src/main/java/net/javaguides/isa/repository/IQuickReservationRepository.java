package net.javaguides.isa.repository;

import net.javaguides.isa.model.FishingInstructor;
import net.javaguides.isa.model.QuickReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IQuickReservationRepository extends JpaRepository<QuickReservation, Long> {
}
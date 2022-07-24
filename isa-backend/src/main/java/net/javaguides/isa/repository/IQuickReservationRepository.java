package net.javaguides.isa.repository;

import net.javaguides.isa.model.QuickReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface IQuickReservationRepository extends JpaRepository<QuickReservation, Long> {
    Set<QuickReservation> findAllByCottage_Id(Long id);
}
package net.javaguides.isa.repository;

import net.javaguides.isa.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface IReservationRepository extends JpaRepository<Reservation, Long> {
    Set<Reservation> findAllByCottage_Id(Long id);
}

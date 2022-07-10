package net.javaguides.isa.repository;

import net.javaguides.isa.model.Boat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBoatRepository extends JpaRepository<Boat, Long> {
    Boat findOneById(Long id);
}
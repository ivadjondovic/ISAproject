package net.javaguides.isa.repository;

import net.javaguides.isa.model.BoatOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBoatOwnerRepository extends JpaRepository<BoatOwner, Long> {
    BoatOwner findOneById(Long id);
}

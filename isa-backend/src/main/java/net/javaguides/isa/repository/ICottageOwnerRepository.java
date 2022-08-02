package net.javaguides.isa.repository;

import net.javaguides.isa.model.CottageOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICottageOwnerRepository extends JpaRepository<CottageOwner, Long> {
    CottageOwner findOneById(Long id);
}
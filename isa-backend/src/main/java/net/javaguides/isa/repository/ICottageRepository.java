package net.javaguides.isa.repository;

import net.javaguides.isa.model.Cottage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICottageRepository extends JpaRepository<Cottage, Long> {
    Cottage findOneById(Long id);
}
package net.javaguides.isa.repository;

import net.javaguides.isa.model.FishingInstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFishingInstructorRepository extends JpaRepository<FishingInstructor, Long> {
}

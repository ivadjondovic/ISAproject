package net.javaguides.isa.repository;
import net.javaguides.isa.model.AdditionalService;
import net.javaguides.isa.model.BoatOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdditionalServiceRepository extends JpaRepository<AdditionalService, Long>{
}

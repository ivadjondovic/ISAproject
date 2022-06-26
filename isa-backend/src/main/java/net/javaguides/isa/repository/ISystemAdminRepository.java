package net.javaguides.isa.repository;

import net.javaguides.isa.model.SystemAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISystemAdminRepository extends JpaRepository<SystemAdmin, Long> {
}

package net.javaguides.isa.repository;

import net.javaguides.isa.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientRepository extends JpaRepository<Client, Long> {
    Client findOneById(Long id);
}

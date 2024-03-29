package net.javaguides.isa.repository;

import net.javaguides.isa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    User findOneByUsername(String username);
    User findOneById(Long id);
}

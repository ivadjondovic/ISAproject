package com.isa.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.isa.project.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername(String username);
	@Query(value = "SELECT type FROM users_table WHERE id = ?1",nativeQuery = true)
	String findTypeById(Long id);
	List<User> findByStatus(String status);
	List<User> findByStatusAndDeleted(String status, Boolean deleted);
	
}

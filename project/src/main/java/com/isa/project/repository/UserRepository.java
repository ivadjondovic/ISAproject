package com.isa.project.repository;

import java.util.List;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.isa.project.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername(String username);
	@Query(value = "SELECT type FROM users_table WHERE id = ?1",nativeQuery = true)
	String findTypeById(Long id);
	List<User> findByStatus(String status);
	List<User> findByStatusAndDeleted(String status, String deleted);
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select u from User u where u.id = :id")
	@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="10000")})
	public User findLockById(@Param("id")Long id);
	
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select u from User u where u.username = :username")
	@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="10000")})
	public User findLockByUsername(@Param("username")String username);
	
}

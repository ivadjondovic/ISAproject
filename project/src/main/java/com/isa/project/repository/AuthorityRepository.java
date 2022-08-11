package com.isa.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.project.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
	Authority findByName(String name);
}

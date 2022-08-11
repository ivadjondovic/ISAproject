package com.isa.project.service;

import java.util.List;

import com.isa.project.model.Authority;

public interface AuthorityService {

	List<Authority> findById(Long id);
	List<Authority> findByName(String name);
}

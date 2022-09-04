package com.isa.project.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.model.Authority;
import com.isa.project.repository.AuthorityRepository;
import com.isa.project.service.AuthorityService;

@Service
public class AuthorityServiceImplementation implements AuthorityService {

  @Autowired
  private AuthorityRepository authorityRepository;

  @Override
  public List<Authority> findById(Long id) {
	  @SuppressWarnings("deprecation")
	Authority auth = this.authorityRepository.getOne(id);
	  List<Authority> auths = new ArrayList<>();
	  auths.add(auth);
	  return auths;
  }

  @Override
  public List<Authority> findByName(String name) {
	  Authority auth = this.authorityRepository.findByName(name);
	  List<Authority> auths = new ArrayList<>();
	  auths.add(auth);
	  return auths;
  }


}

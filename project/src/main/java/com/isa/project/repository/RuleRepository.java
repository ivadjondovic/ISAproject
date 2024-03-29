package com.isa.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.project.model.Rule;

@Repository
public interface RuleRepository extends JpaRepository<Rule, Long> {

}

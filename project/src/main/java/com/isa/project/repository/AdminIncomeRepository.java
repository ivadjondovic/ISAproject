package com.isa.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.project.model.Admin;
import com.isa.project.model.AdminIncome;

public interface AdminIncomeRepository extends JpaRepository<AdminIncome, Long> {

	public List<AdminIncome> findByAdmin(Admin admin);
	
}

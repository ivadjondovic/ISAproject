package com.isa.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.project.model.Instructor;
import com.isa.project.model.InstructorIncome;

public interface InstructorIncomeRepository extends JpaRepository<InstructorIncome, Long> {

	public List<InstructorIncome> findByInstructor(Instructor instructor);
	
}

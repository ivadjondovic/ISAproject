package com.isa.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.project.model.BoatComplaint;

@Repository
public interface BoatComplaintRepository extends JpaRepository<BoatComplaint, Long> {

}

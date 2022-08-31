package com.isa.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.project.model.CottageComplaint;

@Repository
public interface CottageComplaintRepository extends JpaRepository<CottageComplaint, Long>  {

}

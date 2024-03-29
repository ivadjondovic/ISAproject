package com.isa.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.project.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long>{

}

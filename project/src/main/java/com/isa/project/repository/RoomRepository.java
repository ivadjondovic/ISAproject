package com.isa.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.project.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

}

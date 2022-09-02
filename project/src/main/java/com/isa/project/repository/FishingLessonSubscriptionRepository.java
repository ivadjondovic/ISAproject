package com.isa.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.project.model.Client;
import com.isa.project.model.FishingLessonSubscription;

@Repository
public interface FishingLessonSubscriptionRepository extends JpaRepository<FishingLessonSubscription, Long> {
	public List<FishingLessonSubscription> findByClient(Client client);

}

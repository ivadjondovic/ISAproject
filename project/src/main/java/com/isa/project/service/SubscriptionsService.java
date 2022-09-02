package com.isa.project.service;

import java.util.List;

import com.isa.project.dto.SubscriptionResponseDTO;

public interface SubscriptionsService {
	
	public List<SubscriptionResponseDTO> getClientSubscriptions(Long clientId);

}

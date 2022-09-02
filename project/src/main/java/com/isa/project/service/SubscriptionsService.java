package com.isa.project.service;

import java.util.List;

import com.isa.project.dto.SubscriptionResponseDTO;
import com.isa.project.dto.UnsubscribeDTO;

public interface SubscriptionsService {
	
	public List<SubscriptionResponseDTO> getClientSubscriptions(Long clientId);
	public SubscriptionResponseDTO unsubscribe(UnsubscribeDTO dto);

}

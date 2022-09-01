package com.isa.project.service;

import com.isa.project.dto.RuleDTO;
import com.isa.project.model.Rule;

public interface RuleService {

	public void deleteRule(Long ruleId, Long lessonId);
	public Rule saveRule(RuleDTO dto);
	public Rule createRule(RuleDTO dto);
}

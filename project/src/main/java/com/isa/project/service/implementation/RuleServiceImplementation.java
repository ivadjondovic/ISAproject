package com.isa.project.service.implementation;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.model.FishingLesson;
import com.isa.project.model.Rule;
import com.isa.project.repository.FishingLessonRepository;
import com.isa.project.repository.RuleRepository;
import com.isa.project.service.RuleService;

@Service
public class RuleServiceImplementation implements RuleService {

	@Autowired
	private RuleRepository ruleRepository;
	
	@Autowired
	private FishingLessonRepository fishingLessonRepository;
	
	@Override
	public void deleteRule(Long ruleId, Long lessonId) {
		
		FishingLesson lesson = fishingLessonRepository.findById(lessonId).get();
		Set<Rule> rules = lesson.getRules();
		
		Rule rule = ruleRepository.findById(ruleId).get();
		
		rules.remove(rule);
		lesson.setRules(rules);
		fishingLessonRepository.save(lesson);
		

	}

	
}

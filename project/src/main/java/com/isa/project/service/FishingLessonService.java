package com.isa.project.service;

import java.util.List;

import com.isa.project.dto.FishingLessonDTO;
import com.isa.project.dto.SortDTO;
import com.isa.project.model.FishingLesson;

public interface FishingLessonService {

	public FishingLesson createFishingLesson(FishingLessonDTO dto);
	public List<FishingLesson> getByInstructorId(Long instructorId);
	public FishingLesson getById(Long id);
	public FishingLesson editFishingLesson(FishingLessonDTO dto);
	public List<FishingLesson> getAll();
	public List<FishingLesson> sort(SortDTO dto);
	public List<FishingLesson> search(String searchTerm);
}

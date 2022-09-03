package com.isa.project.service;

import java.util.List;

import com.isa.project.dto.DateSearchDTO;
import com.isa.project.dto.FishingLessonDTO;
import com.isa.project.dto.ReservationSearchDTO;
import com.isa.project.dto.SearchParamsDTO;
import com.isa.project.dto.SortDTO;
import com.isa.project.model.FishingLesson;

public interface FishingLessonService {

	public FishingLesson createFishingLesson(FishingLessonDTO dto);
	public List<FishingLesson> getByInstructorId(Long instructorId);
	public FishingLesson getById(Long id);
	public FishingLesson getByIdForInstructor(Long id);
	public FishingLesson editFishingLesson(FishingLessonDTO dto);
	public List<FishingLesson> getAll();
	public List<FishingLesson> sort(SortDTO dto);
	public List<FishingLesson> search(String searchTerm);
	public List<FishingLesson> getAvailableLessons(ReservationSearchDTO dto);
	public List<FishingLesson> lessonsAvailableForCertainDate(DateSearchDTO dto);
	public List<FishingLesson> searchForInstructor(String searchTerm, Long id);
	public List<FishingLesson> getLessonsByClientSubscription(Long clientId);
	public List<FishingLesson> sortAvailableLessons(ReservationSearchDTO dto);
	public List<FishingLesson> searchByMoreParams(SearchParamsDTO dto);
	public List<FishingLesson> getAvailableLessonsForInstructor(ReservationSearchDTO dto);
}

package com.isa.project.service.implementation;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.dto.ImageDTO;
import com.isa.project.model.FishingLesson;
import com.isa.project.model.Image;
import com.isa.project.repository.FishingLessonRepository;
import com.isa.project.repository.ImageRepository;
import com.isa.project.service.ImageService;

@Service
public class ImageServiceImplementation implements ImageService {

	@Autowired
	private ImageRepository imageRepository;
	
	@Autowired
	private FishingLessonRepository fishingLessonRepository;
	
	@Override
	public void deleteImage(Long imageId, Long lessonId) {
		
		Image image = imageRepository.findById(imageId).get();
		
		FishingLesson lesson = fishingLessonRepository.findById(lessonId).get();
		
		Set<Image> images = lesson.getImages();
		
		images.remove(image);
		lesson.setImages(images);
		fishingLessonRepository.save(lesson);
	
		
	}

	@Override
	public Image createImage(ImageDTO dto) {
		
		if(dto.getPath().equals("")) {
			return null;
		}
		Image image = new Image();
		
		image.setPath(dto.getPath());
		
		Image saveImage = imageRepository.save(image);
		
		FishingLesson lesson = fishingLessonRepository.findById(dto.getId()).get();
		
		Set<Image> images = lesson.getImages();
		
		images.add(image);
		
		lesson.setImages(images);
		
		fishingLessonRepository.save(lesson);
		
		
		return saveImage;
	}
	
	

}

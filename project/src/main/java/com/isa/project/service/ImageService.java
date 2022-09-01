package com.isa.project.service;

import com.isa.project.dto.ImageDTO;
import com.isa.project.model.Image;

public interface ImageService {

	public void deleteImage(Long imageId, Long lessonId);
	public Image createImage(ImageDTO dto);
}

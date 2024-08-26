package prova.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prova.model.Image;
import prova.repository.ImageRepository;


@Service
public class ImageService {
	
	@Autowired
	private ImageRepository imageRepository;

	public byte[] getImage(Long id) {
		Image image = imageRepository.findById(id).orElseThrow();
		return image.getImageData();
	}
}
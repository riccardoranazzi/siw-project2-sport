package prova.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import prova.model.Image;
import prova.model.Team;
import prova.repository.ImageRepository;
import prova.repository.ProductRepository;

@Service
public class ProductService {
	
 @Autowired
 private ProductRepository productRepository;
 @Autowired
 private ImageRepository imageRepository;
 
 public Team createProduct(String name, MultipartFile imageFile) throws IOException {
     Image image = new Image();
     image.setImageData(imageFile.getBytes());
     image.setType(imageFile.getContentType());
     image = imageRepository.save(image);
     Team product = new Team();
     product.setName(name);
     product.setImage(image);
     return productRepository.save(product);
 }
 
 public List<Team> getAllProducts() {
     return productRepository.findAll();
 }

}
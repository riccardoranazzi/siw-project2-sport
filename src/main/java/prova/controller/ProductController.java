package prova.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import prova.model.Team;
import prova.service.ProductService;

//ProductController.java

@Controller
public class ProductController {
	
 @Autowired
 private ProductService productService;

 @PostMapping("/formNewTeam")
 public String createProduct(@RequestParam("name") String name, @RequestParam("image") MultipartFile imageFile) throws IOException {
     productService.createProduct(name, imageFile);
     return "redirect:/teams";
 }
 
 @GetMapping("/formNewTeam")
 public String showForm(Model model) {
	 model.addAttribute("product", new Team());
	 return "formNewTeam";
 }
 
 @GetMapping("/teams")
 public String showProducts(Model model) {
	model.addAttribute("teams", teamService.getAllTeams());
	return "team";
 }

}

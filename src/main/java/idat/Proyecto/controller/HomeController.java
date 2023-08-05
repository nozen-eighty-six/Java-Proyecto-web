package idat.Proyecto.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import idat.Proyecto.service.ProductoService;

@Controller
@RequestMapping("/")//Apunte a la raíz
public class HomeController {
	
	private final Logger log= LoggerFactory.getLogger(HomeController.class);
	

	@Autowired
	private ProductoService prs;

	@GetMapping("")
	public String home(Model model) {
		model.addAttribute("productos", prs.findAll());
		
		return "usuario/home";
		
	}
	
	@GetMapping("productohome/{id}")
	public String productoHome(@PathVariable Integer id, Model model) {
		log.info("Id producto enviado como parámtero{}", id);
		
		return "usuario/productohome";
	}
}

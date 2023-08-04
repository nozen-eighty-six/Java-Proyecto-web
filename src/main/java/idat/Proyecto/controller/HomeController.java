package idat.Proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import idat.Proyecto.service.ProductoService;

@Controller
@RequestMapping("/")//Apunte a la raíz
public class HomeController {

	@Autowired
	private ProductoService prs;

	@GetMapping("")
	public String home(Model model) {
		model.addAttribute("productos", prs.findAll());
		
		return "usuario/home";
		
	}
}

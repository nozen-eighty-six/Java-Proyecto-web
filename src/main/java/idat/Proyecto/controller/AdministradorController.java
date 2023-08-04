package idat.Proyecto.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import idat.Proyecto.entity.Producto;
import idat.Proyecto.service.ProductoService;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {

	@Autowired
	private ProductoService prs;
	
	@GetMapping("")
	public String home_GET(Model model) {
		List<Producto> productos = prs.findAll();
		model.addAttribute("productos", productos);
		return "administrador/home";
	}
	
	
	
}

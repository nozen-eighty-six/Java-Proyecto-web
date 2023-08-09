package idat.Proyecto.controller;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import idat.Proyecto.entity.Producto;
import idat.Proyecto.service.ProductoService;
import idat.Proyecto.service.UsuarioService;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {

	Logger log = LoggerFactory.getLogger(AdministradorController.class);
	
	@Autowired
	private ProductoService prs;
	
	@Autowired
	private UsuarioService us;
	
	@GetMapping("")
	public String home_GET(Model model) {
		List<Producto> productos = prs.findAll();
		model.addAttribute("productos", productos);
		return "administrador/home";
	}
	
	@GetMapping("/usuarios")
	public String usuario (Model model) {
		//Lista de usuarios
		log.info("usuarios: {}", us.findAll());
		model.addAttribute("usuarios", us.findAll());
		return "administrador/usuarios";
	}
	
	
	
	
}

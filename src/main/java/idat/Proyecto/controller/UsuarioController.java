package idat.Proyecto.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import idat.Proyecto.entity.Usuario;
import idat.Proyecto.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	private final Logger log = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private UsuarioService us;
	
	@GetMapping("/registro")
	public String registro_GET() {
		
		return "usuario/registro";
	}
	
	@PostMapping("/save")
	public String save(Usuario usuario) {
		log.info("Usuario registro: {}", usuario);
		//Asignamos el tipo de usuario
		usuario.setTipo("USER");
		us.save(usuario);
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String login() {
		
		return "usuario/login";
	}
	
	

}

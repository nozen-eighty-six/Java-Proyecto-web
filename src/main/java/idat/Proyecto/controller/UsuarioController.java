package idat.Proyecto.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import idat.Proyecto.entity.Orden;
import idat.Proyecto.entity.Usuario;
import idat.Proyecto.service.OrdenService;
import idat.Proyecto.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	private final Logger log = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private UsuarioService us;
	
	@Autowired
	private OrdenService os;
	
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
	
	@PostMapping("/acceder")
	public String acceder(Usuario usuario, HttpSession session) {
		log.info("Acceso: {}", usuario);
		
		Optional<Usuario> user = us.findByMail(usuario.getMail());
		log.info("Usuario de db: {}", user.get());
		
		
		//Si está presente
		if(user.isPresent()) {
			//Para usar el id en el resto de la aplicación
			session.setAttribute("idusuario", user.get().getId());
			
			//Valido el tipo
			if(user.get().getTipo().equals("ADMIN")) {
				return "redirect:/administrador";
				
			}
			else {
				
				return "redirect:/";
			}
		}
		else {
			log.info("Usuario no existe");
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/compras")
	public String compraUsuario(Model model, HttpSession session) {
		//Usuario
		Usuario usari = new Usuario();
		usari = us.findById(Integer.parseInt(session.getAttribute("idusuario").toString()));
		
		List<Orden> ordenes = os.findByUsuario(usari);
		
		//Lista órdenes
		model.addAttribute("ordenes", ordenes);
		
		//Session
		model.addAttribute("sesion", session.getAttribute("idusuario"));
		return "usuario/compras";
	}
	
	

}

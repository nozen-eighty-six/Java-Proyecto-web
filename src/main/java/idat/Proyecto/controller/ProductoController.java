package idat.Proyecto.controller;

import java.util.Date;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import idat.Proyecto.entity.Categoria;
import idat.Proyecto.entity.Producto;
import idat.Proyecto.entity.Usuario;
import idat.Proyecto.service.ProductoService;

@Controller
@RequestMapping("/productos")
public class ProductoController {
	
	
	private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
	
	
	//Inyección
	@Autowired
	private ProductoService prs;
	@GetMapping("")
	public String show() {
		
		
		return "productos/show";
	}
	
	@GetMapping("/create")
	public String create() {
		
		return "productos/create";
	}
	
	
	@PostMapping("/save")
	public String save(Producto producto) {
		
		
		//Prueba
		LOGGER.info("Este es el objeto producto{}", producto);
		
		//Necesitamos un user
		Usuario u = new Usuario(1, "", "", "", "", "", "", "");
		Categoria c = new Categoria(1, "Corbata", true,new Date());
		
		//Guardamos
		producto.setUsuario(u);
		producto.setCategoria(c);
		prs.save(producto);
			
		
		return "redirect:/productos";
	}

}

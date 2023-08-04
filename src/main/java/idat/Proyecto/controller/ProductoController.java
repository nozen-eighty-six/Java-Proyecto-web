package idat.Proyecto.controller;

import java.util.Date;
import java.util.Optional;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


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
	public String show(Model model) {
		
		model.addAttribute("bProductos", prs.findAll());
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
		
		//Guardamos
		producto.setUsuario(u);
		prs.save(producto);
			
		
		return "redirect:/productos";
	}
	
	@GetMapping("/editar/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		Producto producto = new Producto();
		Optional<Producto> optionalProducto = prs.get(id);
		
		//Asignamos
		producto = optionalProducto.get();
		
		//Probamos
		LOGGER.info("Producto buscado: {}", producto);
		
		//Enviamos el modelo con el objeto a editar
		model.addAttribute("producto", producto);
		
		return "productos/edit";
	}
	
	@PostMapping("/update")
	public String update(Producto producto) {
		prs.update(producto);
		return "redirect:/productos";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		
		prs.delete(id);
		return "redirect:/productos";
	}

}

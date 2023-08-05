package idat.Proyecto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import idat.Proyecto.entity.DetalleOrden;
import idat.Proyecto.entity.Orden;
import idat.Proyecto.entity.Producto;
import idat.Proyecto.service.ProductoService;

@Controller
@RequestMapping("/")//Apunte a la raíz
public class HomeController {
	
	private final Logger log= LoggerFactory.getLogger(HomeController.class);
	
	
	//Almacenar los detalles de la orden
	List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();

	//Almacena los datos de la orden
	Orden orden = new Orden();
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
		Producto producto = new Producto();
		Optional<Producto> optionalproducto =prs.get(id);
		
		//Asignamos
		producto = optionalproducto.get();
		
		model.addAttribute("producto", producto);
		return "usuario/productohome";
	}
	
	@PostMapping("/cart")
	public String addproductoCarrito(@RequestParam Integer id, @RequestParam Integer cantidad) {
		
		DetalleOrden detalleOrden = new DetalleOrden();
		Producto producto = new Producto();
		double sumaTotal=0;
		
		//Buscamos el producto
		Optional<Producto> optionalProducto = prs.get(id);
		log.info("Producto añadido:{}", optionalProducto.get());
		log.info("Cantidad: {}", cantidad);
		
		return "usuario/carrito";
	}
}

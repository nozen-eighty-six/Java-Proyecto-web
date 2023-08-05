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
import idat.Proyecto.entity.Usuario;
import idat.Proyecto.service.ProductoService;
import idat.Proyecto.service.UsuarioService;

@Controller
@RequestMapping("/") // Apunte a la raíz
public class HomeController {

	private final Logger log = LoggerFactory.getLogger(HomeController.class);

	// Almacenar los detalles de la orden
	List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();

	// Almacena los datos de la orden
	Orden orden = new Orden();
	@Autowired
	private ProductoService prs;
	
	@Autowired
	private UsuarioService us;

	@GetMapping("")
	public String home(Model model) {
		model.addAttribute("productos", prs.findAll());

		return "usuario/home";

	}

	@GetMapping("productohome/{id}")
	public String productoHome(@PathVariable Integer id, Model model) {
		log.info("Id producto enviado como parámtero{}", id);
		Producto producto = new Producto();
		Optional<Producto> optionalproducto = prs.get(id);

		// Asignamos
		producto = optionalproducto.get();

		model.addAttribute("producto", producto);
		return "usuario/productohome";
	}

	@PostMapping("/cart")
	public String addproductoCarrito(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) {

		DetalleOrden detalleOrden = new DetalleOrden();
		Producto producto = new Producto();
		double sumaTotal = 0;

		// Buscamos el producto
		Optional<Producto> optionalProducto = prs.get(id);
		log.info("Producto añadido:{}", optionalProducto.get());
		log.info("Cantidad: {}", cantidad);

		// Asignamos
		producto = optionalProducto.get();

		// Asignamos el detalle
		detalleOrden.setCantidad((double) cantidad);
		detalleOrden.setPrecio(producto.getPrecio());
		detalleOrden.setNombre(producto.getNombre());
		detalleOrden.setTotal(producto.getPrecio() * (double) cantidad);
		detalleOrden.setProducto(producto);

		// Validación para que el producto no se agregue dos veces
		Integer idProducto = producto.getId();
		Boolean ingresado = detalles.stream().anyMatch(p -> p.getProducto().getId() == idProducto);

		if (!ingresado) {

			// Asiganmos a nuestra lista global
			detalles.add(detalleOrden);
		}

		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();
		orden.setTotal(sumaTotal);
		// Pasar a la vista
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);

		return "usuario/carrito";
	}

	// Quitar un producto del carrito
	@GetMapping("delete/{id}")
	public String deleteProductoCart(@PathVariable Integer id, Model model) {

		// Lista nueva de productos, para guardarla en el global
		List<DetalleOrden> ordenesNuevas = new ArrayList<DetalleOrden>();

		for (DetalleOrden dto : detalles) {
			// Agrupamos los detalles que sean diferentes al id del parámetro,
			// por tanto el que se quiere eliminar no se agregado a está nueva orden
			if (dto.getProducto().getId() != id) {
				ordenesNuevas.add(dto);

			}

		}
		// Nueva lista;
		detalles = ordenesNuevas;
		// Se restablece el valor del total a pagar
		double sumaTotal = 0;

		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();
		orden.setTotal(sumaTotal);
		// Pasar a la vista
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);
		return "usuario/carrito";

	}

	@GetMapping("/getcart")
	public String getcart(Model model) {

		// Añadimos el detalle y la orden
		// Pasar a la vista
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);

		return "/usuario/carrito";
	}

	@GetMapping("/order")
	public String order(Model model) {

		Usuario usuario = us.get(1).get();
		
		// Añadimos el detalle y la orden
		// Pasar a la vista
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);
		
		//Pasar el usuario
		model.addAttribute("usuario", usuario);
		return "usuario/resumenorden";
	}
}

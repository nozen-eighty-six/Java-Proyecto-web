package idat.Proyecto.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

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
import idat.Proyecto.entity.Venta;
import idat.Proyecto.service.DetalleOrdenService;
import idat.Proyecto.service.OrdenService;
import idat.Proyecto.service.ProductoService;
import idat.Proyecto.service.UsuarioService;
import idat.Proyecto.service.VentaService;

import java.util.Date;

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

	@Autowired
	private OrdenService os;

	@Autowired
	private DetalleOrdenService ods;

	@Autowired
	private VentaService vs;

	Collection<Producto> productos;

	@GetMapping("")
	public String home(Model model, HttpSession session) {
		// Verificar la sesión, mostrará el id
		log.info("Sesión del usuario {}", session.getAttribute("idusuario"));

		model.addAttribute("productos", prs.findAll());

		// Session
		model.addAttribute("sesion", session.getAttribute("idusuario"));

		return "usuario/home";

	}

	@GetMapping("productohome/{id}")
	public String productoHome(@PathVariable Integer id, Model model, HttpSession session) {
		log.info("Id producto enviado como parámtero{}", id);
		Producto producto = new Producto();
		Optional<Producto> optionalproducto = prs.get(id);

		// Asignamos
		producto = optionalproducto.get();

		model.addAttribute("producto", producto);

		// Session
		model.addAttribute("sesion", session.getAttribute("idusuario"));
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
	public String getcart(Model model, HttpSession session) {

		// Añadimos el detalle y la orden
		// Pasar a la vista
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);
		// Session
		model.addAttribute("session", session.getAttribute("idusuario"));

		return "/usuario/carrito";
	}

	@GetMapping("/order")
	public String order(Model model, HttpSession session) {

		Usuario usuario = us.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();

		// Añadimos el detalle y la orden
		// Pasar a la vista
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);

		// Pasar el usuario
		model.addAttribute("usuario", usuario);
		return "usuario/resumenorden";
	}

	@GetMapping("/saveOrder")
	public String saveOrder(HttpSession session, Model model) {
		Date fechaActual = new Date();
		orden.setFechaCreacion(fechaActual);
		orden.setNumero(os.getOrden());

		// Usuario
		Usuario usuario = us.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
		orden.setUsuario(usuario);

		// Guardamos en la bs
		os.save(orden);

		// Guardamos
		for (DetalleOrden dt : detalles) {
			dt.setOrden(orden);
			ods.save(dt);

		}

		List<Orden> ordenes = new ArrayList<Orden>();
		ordenes = os.findByUsuario(usuario);
		log.info("ordenes: {}", ordenes);

		// Problema de la propiedad es nulla
		double sumaUser = 0;
		for (Orden or : ordenes) {
			Double sumaTotal = or.getTotal();
			if (sumaTotal != null) {

				sumaUser += sumaTotal.doubleValue();
			}

		}

		log.info("suma: {}", sumaUser);
		Venta ventaUser = new Venta();
		ventaUser.setUsuario(usuario);
		ventaUser.setTotal(sumaUser);
		ventaUser.setFechaCreacion(fechaActual);
		vs.save(ventaUser);

		// Una vez guardado limpiamos todo
		orden = new Orden();
		detalles.clear();

		// model.addAttribute("userdetalle", ventaUser);

		return "usuario/compraExitosa";
	}

	@PostMapping("/search")
	public String searchProduct(@RequestParam String nombre, Model model) {
		log.info("Nombre del producto: {}", nombre);
		// Cadena de texto Transform
		String nuevaCadena = nombre.substring(0, 1).toUpperCase() + nombre.substring(1);

		// Lista que coincidan con el texto de buscar
		List<Producto> productos = prs.findAll().stream().filter(pr -> pr.getNombre().contains(nuevaCadena))
				.collect(Collectors.toList());
		model.addAttribute("productos", productos);
		return "usuario/home";
	}
}

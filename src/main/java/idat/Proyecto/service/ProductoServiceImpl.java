package idat.Proyecto.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.Proyecto.entity.Producto;
import idat.Proyecto.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {

	
	@Autowired
	private ProductoRepository pr;
	
	@Override
	public Producto save(Producto producto) {
		// TODO Auto-generated method stub
		return pr.save(producto);
	}

	@Override
	public Optional<Producto> get(Integer id) {
		// TODO Auto-generated method stub
		return pr.findById(id);
	}

	@Override
	public void update(Producto producto) {
		pr.save(producto);
		
	}

	@Override
	public void delete(Integer id) {
		pr.deleteById(id);
		
	}

}

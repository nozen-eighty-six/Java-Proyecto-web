package idat.Proyecto.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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
	@Transactional
	public void update(Producto producto) {
		pr.save(producto);
		
	}

	@Override
	public void delete(Integer id) {
		pr.deleteById(id);
		
	}

	@Override
	public List<Producto> findAll() {
		// TODO Auto-generated method stub
		return pr.findAll();
	}

}

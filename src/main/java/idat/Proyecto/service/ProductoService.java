package idat.Proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import idat.Proyecto.entity.Producto;

@Repository
public interface ProductoService {
	
	public abstract Producto save(Producto producto);
	public abstract Optional<Producto> get(Integer id);
	public abstract void update(Producto producto);
	public abstract void delete(Integer id);
	public abstract List<Producto> findAll();
}

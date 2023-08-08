package idat.Proyecto.service;

import java.util.List;

import idat.Proyecto.entity.Orden;
import idat.Proyecto.entity.Usuario;

public interface OrdenService {

	public abstract Orden save(Orden orden);
	public abstract List<Orden> findAll();
	public abstract String getOrden();
	public abstract List<Orden> findByUsuario(Usuario usuario);
}

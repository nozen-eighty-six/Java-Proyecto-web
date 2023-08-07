package idat.Proyecto.service;

import java.util.List;

import idat.Proyecto.entity.Orden;

public interface OrdenService {

	public abstract Orden save(Orden orden);
	public abstract List<Orden> findAll();
	public abstract String getOrden();
}

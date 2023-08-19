package idat.Proyecto.service;

import java.util.Optional;

import idat.Proyecto.entity.DetalleOrden;

public interface DetalleOrdenService {

	public abstract DetalleOrden save(DetalleOrden dto);
	public Optional<DetalleOrden> findDetalleById(Integer ordenid);
}

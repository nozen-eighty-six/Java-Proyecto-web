package idat.Proyecto.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.Proyecto.entity.DetalleOrden;
import idat.Proyecto.repository.DetalleOrdenRepository;

@Service
public class DetalleOrdenServiceImpl implements DetalleOrdenService {

	@Autowired
	private DetalleOrdenRepository dtr;
	
	@Override
	public DetalleOrden save(DetalleOrden dto) {
		// TODO Auto-generated method stub
		return dtr.save(dto);
	}

	@Override
	public Optional<DetalleOrden> findDetalleById(Integer ordenid) {
		// TODO Auto-generated method stub
		return dtr.findDetalleById(ordenid);
	}
	
	
	
}


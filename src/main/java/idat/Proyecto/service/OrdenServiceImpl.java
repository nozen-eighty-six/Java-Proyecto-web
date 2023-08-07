package idat.Proyecto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.Proyecto.entity.Orden;
import idat.Proyecto.repository.OrdenRepository;

@Service
public class OrdenServiceImpl implements OrdenService{

	@Autowired
	private OrdenRepository or;
	
	@Override
	public Orden save(Orden orden) {
		// TODO Auto-generated method stub
		return or.save(orden);
	}

}

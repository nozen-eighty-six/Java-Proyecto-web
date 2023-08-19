package idat.Proyecto.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.Proyecto.entity.Venta;
import idat.Proyecto.repository.VentaRepository;

@Service
public class VentaServiceImpl  implements VentaService{

	@Autowired
	private VentaRepository vr;

	@Override
	public void save(Venta venta) {
		vr.save(venta);
		
	}
	


}

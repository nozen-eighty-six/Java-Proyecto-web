package idat.Proyecto.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.Proyecto.entity.Orden;
import idat.Proyecto.entity.Usuario;
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

	@Override
	public List<Orden> findAll() {
		// TODO Auto-generated method stub
		return or.findAll();
	}


	@Override
	public String getOrden() {
		int numero=0;
		String numeroConcatenado ="";//El numero de la orden
		
		List<Orden> ordenes = or.findAll();
		
		List<Integer> numeros = new ArrayList<Integer>();
		
		ordenes.stream().forEach(o->numeros.add(Integer.parseInt(o.getNumero())));

		if(ordenes.isEmpty()) {
			
			numero = 1;
		}
		else {
			numero = numeros.stream().max(Integer::compare).get();
			numero++;
			
		}
		
		if(numero <10) {
			
			numeroConcatenado = "0000000000"+String.valueOf(numero);
			
		}
		else if(numero>=10 && numero<=99 ) {
			
			numeroConcatenado = "000000000"+String.valueOf(numero);
		}
		
		return numeroConcatenado;
	}

	@Override
	public List<Orden> findByUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return or.findByUsuario(usuario);
	}

	@Override
	public Optional<Orden> findById(Integer id) {
		// TODO Auto-generated method stub
		return or.findById(id);
	}


}

package idat.Proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.Proyecto.entity.Usuario;
import idat.Proyecto.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository ur;
	
	@Override
	public Usuario save(Usuario Usuario) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void update(Usuario Usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Optional<Usuario> get(Integer id) {
		// TODO Auto-generated method stub
		return ur.findById(id);
	}








}

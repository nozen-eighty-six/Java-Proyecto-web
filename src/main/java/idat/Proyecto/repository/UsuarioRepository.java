package idat.Proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idat.Proyecto.entity.Usuario;


@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Integer>{

}

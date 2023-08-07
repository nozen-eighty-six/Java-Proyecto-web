package idat.Proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idat.Proyecto.entity.Usuario;
import java.util.List;
import java.util.Optional;



@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Integer>{

	Optional<Usuario> findByMail(String mail);
}

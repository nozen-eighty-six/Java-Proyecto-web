package idat.Proyecto.repository;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

import idat.Proyecto.entity.Orden;
import idat.Proyecto.entity.Usuario;

@Configuration
public interface OrdenRepository extends JpaRepository<Orden, Integer>{

	List<Orden> findByUsuario(Usuario usuario);
}

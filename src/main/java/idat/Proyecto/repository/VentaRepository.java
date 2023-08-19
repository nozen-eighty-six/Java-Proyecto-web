package idat.Proyecto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import idat.Proyecto.entity.Venta;

public interface VentaRepository extends JpaRepository<Venta, Integer> {
	
	/*
	@Query(value = "SELECT o.usuario_id,p.id 'producto_id',o.id,o.total, o.fechaCreacion FROM detalles d Join ordenes o  On d.orden_id = o.id Join productos p On d.producto_id = p.id where o.usuario_id = 3;", nativeQuery = true)
Optional<Venta> detallesUsuarioPorId(@Param("usuarioId") Integer usuarioId);*/


}

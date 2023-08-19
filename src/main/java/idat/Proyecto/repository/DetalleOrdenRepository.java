package idat.Proyecto.repository;

import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import idat.Proyecto.entity.DetalleOrden;

public interface DetalleOrdenRepository extends JpaRepository<DetalleOrden, Integer> {

	@Query(value = "Select * from detalles where orden_id=?", nativeQuery = true)
	public Optional<DetalleOrden> findDetalleById(Integer ordenid);
}

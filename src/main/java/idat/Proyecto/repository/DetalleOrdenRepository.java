package idat.Proyecto.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

import idat.Proyecto.entity.DetalleOrden;

@Configuration
public interface DetalleOrdenRepository extends JpaRepository<DetalleOrden, Integer> {

}

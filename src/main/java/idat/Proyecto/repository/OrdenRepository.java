package idat.Proyecto.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

import idat.Proyecto.entity.Orden;

@Configuration
public interface OrdenRepository extends JpaRepository<Orden, Integer>{

}

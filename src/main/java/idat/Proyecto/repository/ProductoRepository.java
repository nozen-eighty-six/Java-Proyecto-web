package idat.Proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idat.Proyecto.entity.Producto;

//Bean
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{

}

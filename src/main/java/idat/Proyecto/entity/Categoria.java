 package idat.Proyecto.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;


@Entity
@Table(name="categorias")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoriaId;
	private String descripcion;
	private Boolean activo;

	private Date fechaRegistro;
	
	@OneToMany(mappedBy = "categoria")
	private List<Producto> productos;
	
	@PrePersist
	protected void fechaHoy() {
		if(fechaRegistro == null) {
			
			fechaRegistro = new Date();
		}
		
	}
	
	public Categoria() {
		// TODO Auto-generated constructor stub
	}

	public Categoria(Integer categoriaId, String descripcion, Boolean activo, Date fechaRegistro) {
		super();
		this.categoriaId = categoriaId;
		this.descripcion = descripcion;
		this.activo = activo;
		this.fechaRegistro = fechaRegistro;
	}

	public Integer getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	@Override
	public String toString() {
		return "Categoria [categoriaId=" + categoriaId + ", descripcion=" + descripcion + ", activo=" + activo
				+ ", fechaRegistro=" + fechaRegistro + "]";
	}
	
	
	
	
}

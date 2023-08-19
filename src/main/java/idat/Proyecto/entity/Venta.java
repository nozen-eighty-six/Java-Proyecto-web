package idat.Proyecto.entity;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ventas")
public class Venta {

	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer id;
	
	private Double total;
	
	private Date fechaCreacion;
	
	@ManyToOne
	private Usuario usuario;
	

	
	
	public Venta() {
		// TODO Auto-generated constructor stub
	}


	public Venta(Integer id, Double total, Date fechaCreacion) {
		super();
		this.id = id;
		this.total = total;
		this.fechaCreacion = fechaCreacion;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Double getTotal() {
		return total;
	}


	public void setTotal(Double total) {
		this.total = total;
	}


	public Date getFechaCreacion() {
		return fechaCreacion;
	}


	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	@Override
	public String toString() {
		return "Venta [id=" + id + ", total=" + total + ", fechaCreacion=" + fechaCreacion + ", usuario=" + usuario
				+ "]";
	}
	
	
	
	
	
}

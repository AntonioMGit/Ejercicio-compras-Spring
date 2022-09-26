package cursojava.spring.pruebapractica2.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the detallecompras database table.
 * 
 */
@Entity
@Table(name="detallecompras", schema = "modelocompras")
@NamedQuery(name="Detallecompra.findAll", query="SELECT d FROM Detallecompra d")
@IdClass(DetallecompraPK.class)
public class Detallecompra implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer cantidad;

	//bi-directional many-to-one association to Articulo
	@Id
	@ManyToOne
	@JoinColumn(name="articulo")
	private Articulo articulo;

	//bi-directional many-to-one association to Compra
	@Id
	@ManyToOne
	@JoinColumn(name="compra")
	private Compra compra;

	public Detallecompra() {
	}


	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Articulo getArticulo() {
		return this.articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public Compra getCompra() {
		return this.compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

}
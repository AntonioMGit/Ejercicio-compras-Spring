package cursojava.spring.pruebapractica2.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the articulos database table.
 * 
 */
@Entity
@Table(name="articulos", schema = "modelocompras")
@NamedQuery(name="Articulo.findAll", query="SELECT a FROM Articulo a")
public class Articulo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String codigo;

	private float altomm;

	private float anchomm;

	private Integer cantidad;

	private String descripcion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable=false, updatable=false)
	private Date fechaalta;

	private float largomm;

	private String nombre;

	private float pesogramos;

	private BigDecimal preciounidad;

	//bi-directional many-to-one association to Detallecompra
	@OneToMany(mappedBy="articulo")
	private Set<Detallecompra> detallecompras;

	public Articulo() {
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public float getAltomm() {
		return this.altomm;
	}

	public void setAltomm(float altomm) {
		this.altomm = altomm;
	}

	public float getAnchomm() {
		return this.anchomm;
	}

	public void setAnchomm(float anchomm) {
		this.anchomm = anchomm;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaalta() {
		return this.fechaalta;
	}

	public void setFechaalta(Date fechaalta) {
		this.fechaalta = fechaalta;
	}

	public float getLargomm() {
		return this.largomm;
	}

	public void setLargomm(float largomm) {
		this.largomm = largomm;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPesogramos() {
		return this.pesogramos;
	}

	public void setPesogramos(float pesogramos) {
		this.pesogramos = pesogramos;
	}

	public BigDecimal getPreciounidad() {
		return this.preciounidad;
	}

	public void setPreciounidad(BigDecimal preciounidad) {
		this.preciounidad = preciounidad;
	}

	public Set<Detallecompra> getDetallecompras() {
		return this.detallecompras;
	}

	public void setDetallecompras(Set<Detallecompra> detallecompras) {
		this.detallecompras = detallecompras;
	}

	public Detallecompra addDetallecompra(Detallecompra detallecompra) {
		getDetallecompras().add(detallecompra);
		detallecompra.setArticulo(this);

		return detallecompra;
	}

	public Detallecompra removeDetallecompra(Detallecompra detallecompra) {
		getDetallecompras().remove(detallecompra);
		detallecompra.setArticulo(null);

		return detallecompra;
	}

}
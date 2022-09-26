package cursojava.spring.pruebapractica2.entidades;



import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the compras database table.
 * 
 */
@Entity
@Table(name="compras", schema = "modelocompras")
@NamedQuery(name="Compra.findAll", query="SELECT c FROM Compra c")
public class Compra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String codigo;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable=false, updatable=false)
	private Date fechaalta;

	private BigDecimal total;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="cliente")
	private Cliente cliente;

	//bi-directional many-to-one association to Detallecompra
	@OneToMany(mappedBy="compra")
	private Set<Detallecompra> detallecompras;

	public Compra() {
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFechaalta() {
		return this.fechaalta;
	}

	public void setFechaalta(Date fechaalta) {
		this.fechaalta = fechaalta;
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<Detallecompra> getDetallecompras() {
		return this.detallecompras;
	}

	public void setDetallecompras(Set<Detallecompra> detallecompras) {
		this.detallecompras = detallecompras;
	}

	public Detallecompra addDetallecompra(Detallecompra detallecompra) {
		getDetallecompras().add(detallecompra);
		detallecompra.setCompra(this);

		return detallecompra;
	}

	public Detallecompra removeDetallecompra(Detallecompra detallecompra) {
		getDetallecompras().remove(detallecompra);
		detallecompra.setCompra(null);

		return detallecompra;
	}

}
package cursojava.spring.pruebapractica2.entidades;


import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the clientes database table.
 * 
 */
@Entity
@Table(name="clientes", schema = "modelocompras")
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String nif;

	private String apellidos;

	private String clave;

	private String comentarios;

	private Integer cp;

	private BigDecimal descuento;

	private String domicilio;

	private String email;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable=false, updatable=false)
	private Date fechaalta;

	@Temporal(TemporalType.DATE)
	private Date fechanacimiento;

	private String localidad;

	private String mediodepago;

	private String nombre;

	private String provincia;

	private String subscripcion;

	private String usuario;

	//bi-directional many-to-one association to Compra
	@OneToMany(mappedBy="cliente")
	private Set<Compra> compras;

	public Cliente() {
	}

	public String getNif() {
		return this.nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public Integer getCp() {
		return this.cp;
	}

	public void setCp(Integer cp) {
		this.cp = cp;
	}

	public BigDecimal getDescuento() {
		return this.descuento;
	}

	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}

	public String getDomicilio() {
		return this.domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaalta() {
		return this.fechaalta;
	}

	public void setFechaalta(Date fechaalta) {
		this.fechaalta = fechaalta;
	}

	public Date getFechanacimiento() {
		return this.fechanacimiento;
	}

	public void setFechanacimiento(Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

	public String getLocalidad() {
		return this.localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getMediodepago() {
		return this.mediodepago;
	}

	public void setMediodepago(String mediodepago) {
		this.mediodepago = mediodepago;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getSubscripcion() {
		return this.subscripcion;
	}

	public void setSubscripcion(String subscripcion) {
		this.subscripcion = subscripcion;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Set<Compra> getCompras() {
		return this.compras;
	}

	public void setCompras(Set<Compra> compras) {
		this.compras = compras;
	}

	public Compra addCompra(Compra compra) {
		getCompras().add(compra);
		compra.setCliente(this);

		return compra;
	}

	public Compra removeCompra(Compra compra) {
		getCompras().remove(compra);
		compra.setCliente(null);

		return compra;
	}

}
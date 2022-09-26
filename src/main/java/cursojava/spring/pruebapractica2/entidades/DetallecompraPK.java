package cursojava.spring.pruebapractica2.entidades;


import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the detallecompras database table.
 * 
 */
public class DetallecompraPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String compra;

	private String articulo;

	public DetallecompraPK() {
	}
	public String getCompra() {
		return this.compra;
	}
	public void setCompra(String compra) {
		this.compra = compra;
	}
	public String getArticulo() {
		return this.articulo;
	}
	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DetallecompraPK)) {
			return false;
		}
		DetallecompraPK castOther = (DetallecompraPK)other;
		return 
			this.compra.equals(castOther.compra)
			&& this.articulo.equals(castOther.articulo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.compra.hashCode();
		hash = hash * prime + this.articulo.hashCode();
		
		return hash;
	}
}
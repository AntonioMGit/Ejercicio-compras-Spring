package cursojava.spring.pruebapractica2.servicios;

import cursojava.spring.pruebapractica2.NegocioException;
import cursojava.spring.pruebapractica2.dtos.DatosCompra;

public interface IServComprar {

	void comprar(DatosCompra datos) throws NegocioException;

}
package cursojava.spring.pruebapractica2.dtos;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatosCompra {
	
	//no se autogenera, asi que lo pongo para no tener problemas luego
	private String codigoCompra;
	private String nifCliente;
	private Map<String, Integer> articulos; //cod articulo -> cantidad
	
}

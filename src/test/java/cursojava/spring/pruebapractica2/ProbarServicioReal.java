package cursojava.spring.pruebapractica2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import cursojava.spring.pruebapractica2.dtos.DatosCompra;
import cursojava.spring.pruebapractica2.servicios.IServComprar;
import cursojava.spring.pruebapractica2.servicios.ServComprar;

@SpringBootTest(
	webEnvironment = WebEnvironment.RANDOM_PORT
)
public class ProbarServicioReal {
	@Autowired
	IServComprar servCompra;

	@Test
	void probarComprarOk() {

		DatosCompra datos = new DatosCompra();
		datos.setNifCliente("001");
		datos.setCodigoCompra("002");
		Map<String, Integer> articulos = new HashMap();
		articulos.put("001", 2);
		datos.setArticulos(articulos);
		
		assertDoesNotThrow(				
				() -> {
					servCompra.comprar(datos);
				}, 
				"Se ha producido error al probar comprar"				
		);
		
	}
	
	@Test
	void probarComprarClienteNoValido() {

		DatosCompra datos = new DatosCompra();
		datos.setNifCliente("999");
		datos.setCodigoCompra("001");
		Map<String, Integer> articulos = new HashMap();
		articulos.put("001", 2);
		datos.setArticulos(articulos);
	
		NegocioException ex = assertThrows(
				NegocioException.class,
				() -> {
					servCompra.comprar(datos);
				},
				"Funciono crear la imputación"			
			);
	}
	
	@Test
	void probarComprarProductoNoExiste() {

		DatosCompra datos = new DatosCompra();
		datos.setNifCliente("001");
		datos.setCodigoCompra("001");
		Map<String, Integer> articulos = new HashMap();
		articulos.put("999", 2);
		datos.setArticulos(articulos);
		
		NegocioException ex = assertThrows(
				NegocioException.class,
				() -> {
					servCompra.comprar(datos);
				},
				"Funciono crear la imputación"			
			);
	}
	
	@Test
	void probarComprarProductoNoDisponible() {

		DatosCompra datos = new DatosCompra();
		datos.setNifCliente("001");
		datos.setCodigoCompra("001");
		Map<String, Integer> articulos = new HashMap();
		articulos.put("001", 99);
		datos.setArticulos(articulos);
		
		NegocioException ex = assertThrows(
				NegocioException.class,
				() -> {
					servCompra.comprar(datos);
				},
				"Funciono crear la imputación"			
			);
	}
}

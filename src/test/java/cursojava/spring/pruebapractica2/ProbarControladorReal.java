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

@SpringBootTest(
	webEnvironment = WebEnvironment.RANDOM_PORT
)
public class ProbarControladorReal {
	@LocalServerPort
	private int puerto;
	
	@Autowired
	private TestRestTemplate rt;
	
	private String url;
	
	@BeforeEach
	void inicializar() {
		url = "http://localhost:" + puerto + "/comprar";
	}

	@Test
	void probarComprarOk() {

		DatosCompra datos = new DatosCompra();
		datos.setNifCliente("001");
		datos.setCodigoCompra("002");
		Map<String, Integer> articulos = new HashMap();
		articulos.put("001", 2);
		datos.setArticulos(articulos);
		
		ResponseEntity<Object> respuestaAlta = rt.postForEntity(url, datos, Object.class);
		
		HttpStatus estado = respuestaAlta.getStatusCode();
		
		assertEquals(HttpStatus.OK, estado, "Código de respuesta no es OK");
	}
	
	@Test
	void probarComprarClienteNoValido() {

		DatosCompra datos = new DatosCompra();
		datos.setNifCliente("999");
		datos.setCodigoCompra("001");
		Map<String, Integer> articulos = new HashMap();
		articulos.put("001", 2);
		datos.setArticulos(articulos);
		
		ResponseEntity<Object> respuestaAlta = rt.postForEntity(url, datos, Object.class);
		
		HttpStatus estado = respuestaAlta.getStatusCode();
		
		//cliente no valido
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, estado, "Código de respuesta no es ---");
	}
	
	@Test
	void probarComprarProductoNoExiste() {

		DatosCompra datos = new DatosCompra();
		datos.setNifCliente("001");
		datos.setCodigoCompra("001");
		Map<String, Integer> articulos = new HashMap();
		articulos.put("999", 2);
		datos.setArticulos(articulos);
		
		ResponseEntity<Object> respuestaAlta = rt.postForEntity(url, datos, Object.class);
		
		HttpStatus estado = respuestaAlta.getStatusCode();
		
		//prod no existe
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, estado, "Código de respuesta no es ---");
	}
	
	@Test
	void probarComprarProductoNoDisponible() {

		DatosCompra datos = new DatosCompra();
		datos.setNifCliente("001");
		datos.setCodigoCompra("001");
		Map<String, Integer> articulos = new HashMap();
		articulos.put("001", 99);
		datos.setArticulos(articulos);
		
		ResponseEntity<Object> respuestaAlta = rt.postForEntity(url, datos, Object.class);
		
		HttpStatus estado = respuestaAlta.getStatusCode();
		
		//prod no disponible
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, estado, "Código de respuesta no es---");
	}
}

package cursojava.spring.pruebapractica2;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import cursojava.spring.pruebapractica2.dtos.DatosCompra;
import cursojava.spring.pruebapractica2.entidades.Articulo;
import cursojava.spring.pruebapractica2.entidades.Cliente;
import cursojava.spring.pruebapractica2.entidades.Compra;
import cursojava.spring.pruebapractica2.entidades.Detallecompra;
import cursojava.spring.pruebapractica2.repositorios.RepoArticulo;
import cursojava.spring.pruebapractica2.repositorios.RepoCliente;
import cursojava.spring.pruebapractica2.repositorios.RepoCompra;
import cursojava.spring.pruebapractica2.repositorios.RepoDetalleCompra;
import cursojava.spring.pruebapractica2.servicios.ServComprar;

@SpringBootTest(
	webEnvironment = WebEnvironment.NONE
)
public class ProbarServicioMock {
	@Mock
	private RepoCliente repoCliente;
	@Mock
	private RepoCompra repoCompra;
	@Mock
	private RepoArticulo repoArticulo;
	@Mock
	private RepoDetalleCompra repoDetalleCompra;
	
	@InjectMocks
	private ServComprar servComprar;
	
	
	@Test
	void probarComprarConTodoOk() {
			
		DatosCompra datos = new DatosCompra();
		datos.setNifCliente("001");
		datos.setCodigoCompra("001");
		Map<String, Integer> articulos = new HashMap();
		articulos.put("001", 2);
		datos.setArticulos(articulos);
		
		Cliente cli = new Cliente();
		cli.setClave("001");
		Articulo art = new Articulo();
		art.setCodigo("001");
		art.setCantidad(6);
		Compra comp = new Compra();
		comp.setCodigo("001");
		Detallecompra dCompra =  new Detallecompra();
		dCompra.setArticulo(art);
		dCompra.setCompra(comp);
		
		when(repoCliente.findById(datos.getNifCliente())).thenReturn(Optional.of(cli));
		when(repoCompra.findById(datos.getCodigoCompra())).thenReturn(Optional.of(comp));
		when(repoArticulo.findById("001")).thenReturn(Optional.of(art));
		
		assertDoesNotThrow(
			() -> {
				servComprar.comprar(datos);
			},
			"No funciono comprar"
		);
	}

	

}

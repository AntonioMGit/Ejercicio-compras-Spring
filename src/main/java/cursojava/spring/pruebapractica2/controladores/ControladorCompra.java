package cursojava.spring.pruebapractica2.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cursojava.spring.pruebapractica2.dtos.DatosCompra;
import cursojava.spring.pruebapractica2.servicios.IServComprar;

@RestController
@RequestMapping("/comprar")
public class ControladorCompra {
	
	@Autowired
	private IServComprar servComprar;
	
	@PostMapping(
			//no hace falta path porque es el /comprar que ya pone arriba
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<?> crear(@RequestBody DatosCompra datos) {
		try {
			servComprar.comprar(datos);
			return ResponseEntity.ok().build();		
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}
	
	@GetMapping(path = "/prue")
	public ResponseEntity<?> probar() {
		try {
			return ResponseEntity.ok().build();		
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}
}

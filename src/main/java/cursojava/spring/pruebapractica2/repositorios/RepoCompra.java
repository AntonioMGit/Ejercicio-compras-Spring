package cursojava.spring.pruebapractica2.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import cursojava.spring.pruebapractica2.entidades.Cliente;
import cursojava.spring.pruebapractica2.entidades.Compra;
import cursojava.spring.pruebapractica2.entidades.Detallecompra;

public interface RepoCompra extends JpaRepository<Compra, String>{

}

package cursojava.spring.pruebapractica2.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import cursojava.spring.pruebapractica2.entidades.Cliente;

public interface RepoCliente extends JpaRepository<Cliente, String>{

}

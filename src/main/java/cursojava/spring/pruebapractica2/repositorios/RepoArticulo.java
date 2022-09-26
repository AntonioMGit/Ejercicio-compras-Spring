package cursojava.spring.pruebapractica2.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import cursojava.spring.pruebapractica2.entidades.Articulo;

public interface RepoArticulo extends JpaRepository<Articulo, String>{

}

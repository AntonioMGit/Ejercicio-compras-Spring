package cursojava.spring.pruebapractica2.servicios;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cursojava.spring.pruebapractica2.NegocioException;
import cursojava.spring.pruebapractica2.dtos.DatosCompra;
import cursojava.spring.pruebapractica2.entidades.Articulo;
import cursojava.spring.pruebapractica2.entidades.Cliente;
import cursojava.spring.pruebapractica2.entidades.Compra;
import cursojava.spring.pruebapractica2.entidades.Detallecompra;
import cursojava.spring.pruebapractica2.repositorios.RepoArticulo;
import cursojava.spring.pruebapractica2.repositorios.RepoCliente;
import cursojava.spring.pruebapractica2.repositorios.RepoCompra;
import cursojava.spring.pruebapractica2.repositorios.RepoDetalleCompra;

@Service
@Transactional(rollbackFor = NegocioException.class)
public class ServComprar implements IServComprar {

	//repos @autowired
	@Autowired
	RepoCliente repoCliente;
	@Autowired
	RepoArticulo repoArticulo;
	@Autowired
	RepoCompra repoCompra;
	@Autowired
	RepoDetalleCompra repoDetallCompra;
	
	@Override
	public void comprar(DatosCompra datos) throws NegocioException {
		Optional<Cliente> optCli = repoCliente.findById(datos.getNifCliente());
		//comprobar existe cliente con nif
		if(optCli.isPresent()) {
			//comprobar existen articulos Y que tienen esas cantidades
			if(existenArticulosYCantidades(datos.getArticulos())) {
				//restar cantidad a los articulos
				restarCantidades(datos.getArticulos());

				Compra compra = new Compra();
				compra.setCodigo(datos.getCodigoCompra());
				compra.setFecha(new Date());
				compra.setCliente(optCli.get());
				repoCompra.save(compra);
				
				Set<Detallecompra> detallecompras = crearDetallesCompra(datos, compra);
				
				compra.setDetallecompras(detallecompras);
				compra.setTotal(sumarPrecios(datos.getArticulos()));
				repoCompra.save(compra);
			}else {
				throw new NegocioException("No existen articulos o no hay cantidades suficientes", null);	
			}
		}else {
			throw new NegocioException("No existe el cliente", null);	
		}		
	}

	private BigDecimal sumarPrecios(Map<String, Integer> articulos) {
		BigDecimal precio = BigDecimal.valueOf(0);
		
		Set<String> setArt = articulos.keySet();
		List<Articulo> listaArticulos = new ArrayList();
		setArt.forEach(a -> {
			listaArticulos.add(repoArticulo.findById(a).get());
		});
		for (int i = 0; i < listaArticulos.size(); i++) {
			BigDecimal cant = BigDecimal.valueOf(articulos.get(listaArticulos.get(i).getCodigo()));
			BigDecimal pre = listaArticulos.get(i).getPreciounidad();
			BigDecimal cantPre = pre.multiply(cant);
			precio = precio.add(cantPre);
		}
	
		return precio;
	}

	private Set<Detallecompra> crearDetallesCompra(DatosCompra datos, Compra compra) {
		Set<String> codigos = datos.getArticulos().keySet();
		
		String[] cod = (String[]) codigos.toArray(new String[codigos.size()]);
		
		Set<Detallecompra> detallesCompra = new HashSet<Detallecompra>();
		
		for (int i = 0; i < cod.length; i++) {
			Detallecompra dc = new Detallecompra();
			dc.setArticulo(repoArticulo.findById(cod[i]).get());
			dc.setCompra(compra);
			dc.setCantidad(datos.getArticulos().get(cod[i]));
			detallesCompra.add(dc);
			repoDetallCompra.save(dc);
		}
		
		return detallesCompra;
	}

	private void restarCantidades(Map<String, Integer> articulos) {
		articulos.forEach((key, cant) -> {
			Articulo art = repoArticulo.findById(key).get();
			art.setCantidad(art.getCantidad() - cant);
			//update al art?
			repoArticulo.save(art);
		});
	}

	private boolean existenArticulosYCantidades(Map<String, Integer> articulos) {
		Set<String> codigos = articulos.keySet();
		
		return codigos.stream()
			.allMatch((a) -> repoArticulo.findById(a).isPresent() 
					&& repoArticulo.findById(a).get().getCantidad() >= articulos.get(a));
	}
	
}

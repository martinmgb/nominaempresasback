package cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.service;

import java.util.List;

import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.model.dto.TrabajadorDTO;

/**
 * Interface de servicio para trabajador
 */
public interface TrabajadorService {

	List<TrabajadorDTO> consultarTodos();

	TrabajadorDTO obtenerTrabajadorPorId(Long id);

	TrabajadorDTO guardarTrabajador(TrabajadorDTO trabajadorDTO);

	TrabajadorDTO modificarTrabajador(TrabajadorDTO trabajadorDTO);

	void eliminarTrabajador(Long id);

	List<TrabajadorDTO> obtenerTrabajadorPorEmpresa(String idEmpresa);

}

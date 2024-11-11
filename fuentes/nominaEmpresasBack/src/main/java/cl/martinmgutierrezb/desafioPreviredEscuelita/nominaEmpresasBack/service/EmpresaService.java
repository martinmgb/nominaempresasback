package cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.service;

import java.util.List;

import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.model.dto.EmpresaDTO;

/**
 * Interface de servicio para empresa
 */
public interface EmpresaService {
	
	List<EmpresaDTO> consultarTodas();

	EmpresaDTO obtenerEmpresaPorId(String id);

	EmpresaDTO guardarEmpresa(EmpresaDTO empresaDTO);

	EmpresaDTO modificarEmpresa(EmpresaDTO empresaDTO);

	void eliminarEmpresa(String id);

}

package cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.exceptions.RutInvalidoException;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.model.dto.EmpresaDTO;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.model.dto.RutDTO;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.model.entity.Empresa;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.model.entity.Trabajador;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.repository.EmpresaRepository;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.service.EmpresaService;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.util.UtilDTO;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.util.validador.RutValidador;

@Service
public class EmpresaServiceImpl implements EmpresaService {

	@Autowired
	private final EmpresaRepository empresaRepository;
	
	/**
	 * Inyección de dependencia de respository para tratamiento de datos de la empresa en bd
	 * @param empresaRepository
	 */
    public EmpresaServiceImpl(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }
	
    /**
     * Método para obtener todas las empresas
     */
	@Override
	public List<EmpresaDTO> consultarTodas() {
		List<Empresa> empresas = empresaRepository.findAll();
		return empresas.stream()
				.map(empresa -> new EmpresaDTO(empresa.getId(), new RutDTO(empresa.getNumeroRut(), empresa.getDigitoVerificador()), empresa.getRazonSocial(), empresa.getFechaInsercion()))
				.collect(Collectors.toList());
	}
	
	/**
	 * Método para obtener una empresa por id
	 * @param id
	 * @return empresa encontrada [EmpresaDTO]
	 */
	@Override
    public EmpresaDTO obtenerEmpresaPorId(String id) {
    	Optional<Empresa> empresa = empresaRepository.findById(id);
    	if(!empresa.isPresent()) {
			throw new EntityNotFoundException("Empresa no encontrada");
		}
    	
    	return UtilDTO.convertirAEmpresaDTO(empresa);
    }

    
	/**
	 * Método para guardar una empresa
	 * @param datos de la empresa [EmpresaDTO]
	 * @return empresa guardada [EmpresaDTO]
	 * @throws RutInvalidoException
	 */
	@Override
    public EmpresaDTO guardarEmpresa(EmpresaDTO empresaDTO) {
		if(!RutValidador.validarRut(empresaDTO.getRut().getNumero(), 
									empresaDTO.getRut().getDigitoVerificador())) {
			throw new RutInvalidoException("El digito verificador no es válido");
		}
		Optional<Empresa> empresa = empresaRepository.findByNumeroRut(empresaDTO.getRut().getNumero());
		
		if(empresa.isPresent()) {
			throw new RutInvalidoException("El rut ya está registrado");
		}
        Empresa empresaEntity = UtilDTO.convertirAEmpresa(empresaDTO);
        Empresa resultado = empresaRepository.save(empresaEntity);
        return UtilDTO.convertirAEmpresaDTO(resultado);
    }

	/**
	 * Método para modificar una empresa
	 * @param datos de la empresa [EmpresaDTO]
	 * @return empresa guardada [EmpresaDTO]
	 * Solo se esta permitiendo modificar la razón social de una empresa existente
	 * @throws EntityNotFoundException ["Empresa no encontrada"]
	 */
	@Override
    public EmpresaDTO modificarEmpresa(EmpresaDTO empresaDTO) {
		Optional<Empresa> empresa = empresaRepository.findById(empresaDTO.getId());
		
		if(!empresa.isPresent()) {
			throw new EntityNotFoundException("Empresa no encontrada");
		}
		
		Empresa empresaEntity = empresa.get();
		empresaEntity.setRazonSocial(empresaDTO.getRazonSocial());
        Empresa resultado = empresaRepository.save(empresaEntity);
        return UtilDTO.convertirAEmpresaDTO(resultado);
    }

    
	/**
	 * Método para eliminar una empresa por su id
	 * @param id
	 * @throws EntityNotFoundException ["Empresa no encontrada"]
	 */
	@Override
    public void eliminarEmpresa(String id) {
		Optional<Empresa> empresa = empresaRepository.findById(id);
		
		if(!empresa.isPresent()) {
			throw new EntityNotFoundException("Empresa no encontrada");
		}
		
    	empresaRepository.deleteById(id);
    }

}

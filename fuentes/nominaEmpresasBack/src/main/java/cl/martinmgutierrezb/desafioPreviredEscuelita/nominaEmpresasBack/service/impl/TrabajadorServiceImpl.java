package cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.exceptions.RutInvalidoException;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.model.dto.TrabajadorDTO;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.model.entity.Empresa;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.model.entity.Trabajador;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.repository.EmpresaRepository;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.repository.TrabajadorRepository;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.service.TrabajadorService;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.util.UtilDTO;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.util.validador.RutValidador;

@Service
public class TrabajadorServiceImpl implements TrabajadorService {

	@Autowired
	private final TrabajadorRepository trabajadorRepository;
	@Autowired
	private final EmpresaRepository empresaRepository;
	
	/**
	 * Inyección de dependencia de respository
	 * @param trabajadorRepository y empresaRepository
	 */
    public TrabajadorServiceImpl(TrabajadorRepository trabajadorRepository, EmpresaRepository empresaRepository) {
        this.trabajadorRepository = trabajadorRepository;
        this.empresaRepository = empresaRepository;
    }
    
    /**
     * Método para obtener todas los trabajadores
     */
	@Override
	public List<TrabajadorDTO> consultarTodos() {
		List<Trabajador> trabajadores = trabajadorRepository.findAll();
		return trabajadores.stream()
					.map(trabajador -> UtilDTO.convertirATrabajadorDTO(trabajador))
					.collect(Collectors.toList());
	}
	
	/**
	 * Método para obtener un trabajador por id
	 * @param id
	 * @return trabajador encontrado [TrabajadorDTO]
	 */
	@Override
	public TrabajadorDTO obtenerTrabajadorPorId(Long id) {
		Optional<Trabajador> trabajador = trabajadorRepository.findById(id);
		if(!trabajador.isPresent()) {
			throw new EntityNotFoundException("Trabajador no encontrado");
		}
		
    	return UtilDTO.convertirATrabajadorDTO(trabajador);
	}
	
	
	/**
	 * Método para guardar un trabajador
	 * @param datos del trabajador [TrabajadorDTO]
	 * @return trabajador guardado [TrabajadorDTO]
	 * @throws EntityNotFoundException
	 * @throws RutInvalidoException
	 */
	@Override
	public TrabajadorDTO guardarTrabajador(TrabajadorDTO trabajadorDTO) {
		Optional<Empresa> empresa = empresaRepository.findById(trabajadorDTO.getEmpresaId());
		
		if(!empresa.isPresent()) {
			throw new EntityNotFoundException("Empresa no encontrada");
		}
		
		if(!RutValidador.validarRut(trabajadorDTO.getRut().getNumero(), 
									trabajadorDTO.getRut().getDigitoVerificador())) {
			throw new RutInvalidoException("El digito verificador no es válido");
		}
		
		Optional<Trabajador> trabajador = trabajadorRepository.findByNumeroRut(trabajadorDTO.getRut().getNumero());
		
		if(trabajador.isPresent()) {
			throw new RutInvalidoException("El rut ya está registrado");
		}
		
		
		Trabajador trabajadorEntity = UtilDTO.convertirATrabajador(trabajadorDTO);
		trabajadorEntity.setEmpresa(empresa.get());
        Trabajador resultado = trabajadorRepository.save(trabajadorEntity);
        return UtilDTO.convertirATrabajadorDTO(resultado);
	}

	/**
	 * Método para modificar un trabajador
	 * @param datos del trabajador [TrabajadorDTO]
	 * @return trabajador modificado [TrabajadorDTO]
	 * Solo se esta permitiendo modificar nombre, apeliidos, direccion y empresa vinculada 
	 * de un trabajador existente
	 * @throws EntityNotFoundException ["Trabajador no encontrado" y "Empresa no encontrada"]
	 */
	@Override
	public TrabajadorDTO modificarTrabajador(TrabajadorDTO trabajadorDTO) {
		Optional<Trabajador> trabajador = trabajadorRepository.findById(trabajadorDTO.getId());
		if(!trabajador.isPresent()) {
			throw new EntityNotFoundException("Trabajador no encontrado");
		}
		
		Optional<Empresa> empresa = empresaRepository.findById(trabajadorDTO.getEmpresaId());
		if(!empresa.isPresent()) {
			throw new EntityNotFoundException("Empresa no encontrada");
		}
		
		Trabajador trabajadorEntity = trabajador.get();
		trabajadorEntity.setNombre(trabajadorDTO.getNombre());
		trabajadorEntity.setApellidoPaterno(trabajadorDTO.getApellidoPaterno());
		trabajadorEntity.setApellidoMaterno(trabajadorDTO.getApellidoMaterno());
		trabajadorEntity.getDireccion().setCalleAvenida(trabajadorDTO.getDireccion().getCalleAvenida());
		trabajadorEntity.getDireccion().setNumero(trabajadorDTO.getDireccion().getNumero());
		trabajadorEntity.getDireccion().setCiudad(trabajadorDTO.getDireccion().getCiudad());
		trabajadorEntity.setEmpresa(empresa.get());
		Trabajador resultado = trabajadorRepository.save(trabajadorEntity);
        return UtilDTO.convertirATrabajadorDTO(resultado);
	}

	/**
	 * Método para eliminar un trabajador por su id
	 * @param id
	 * @throws EntityNotFoundException ["Trabajador no encontrado"]
	 */
	@Override
	public void eliminarTrabajador(Long id) {
		Optional<Trabajador> trabajador = trabajadorRepository.findById(id);
		if(!trabajador.isPresent()) {
			throw new EntityNotFoundException("Trabajador no encontrado");
		}
		
		trabajadorRepository.deleteById(id);
	}
	
	/**
	 * Método para obetener los trabajadores de una empresa
	 * @param idEmpresa
	 * @return trabajadores de la empresa [List<TrabajadorDTO>]
	 */
	@Override
	public List<TrabajadorDTO> obtenerTrabajadorPorEmpresa(String idEmpresa) {
		Optional<Empresa> empresa = empresaRepository.findById(idEmpresa);
		if(!empresa.isPresent()) {
			throw new EntityNotFoundException("Empresa no encontrada");
		}
		
		List<Trabajador> trabajadores = trabajadorRepository.findByEmpresaId(idEmpresa);
		return trabajadores.stream()
				.map(trabajador -> UtilDTO.convertirATrabajadorDTO(trabajador))
				.collect(Collectors.toList());
	}
}

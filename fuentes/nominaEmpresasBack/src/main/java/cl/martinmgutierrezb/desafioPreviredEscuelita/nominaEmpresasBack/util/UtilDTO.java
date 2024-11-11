package cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.util;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.model.dto.DireccionDTO;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.model.dto.EmpresaDTO;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.model.dto.RutDTO;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.model.dto.TrabajadorDTO;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.model.entity.Direccion;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.model.entity.Empresa;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.model.entity.Trabajador;

/**
 * Clase utilitaria para las conversiones entre instancias 
 * de una entidad y un objeto de transferencia de datos
 * esto separar el modelo de la expocisión de datos y bajar el acoplamiento
 * haciendo más facil de mantener el proyecto.
 */
public class UtilDTO {
	
	/**
	 * Metodo pasa los datos de un DTO a una clase Entity
	 * @param empresaDTO Recibi una instancia de de transferencia de datos para para ser traspasar datos
	 * @return empresa Retorna una instancia de entidad para luego ser usada por los repositorios
	 */
	public static Empresa convertirAEmpresa(EmpresaDTO empresaDTO) {
		Empresa empresa = new Empresa();
		empresa.setId(empresaDTO.getId());
		empresa.setNumeroRut(empresaDTO.getRut().getNumero());
		empresa.setDigitoVerificador(empresaDTO.getRut().getDigitoVerificador());
		empresa.setRazonSocial(empresaDTO.getRazonSocial());
		empresa.setFechaInsercion(empresa.getFechaInsercion());
		return empresa;
	}
	
	/**
	 * Metodo pasa los datos de un Optional Entity a un DTO para enviar al consumidor
	 * @param opctionalEmpresa Recibi una instancia Optional<Empresa> para traspasar datos
	 * @return empresaDTO Retorna una instancia DTO para enviar al consumidor
	 */
	public static EmpresaDTO convertirAEmpresaDTO(Optional<Empresa> opctionalEmpresa) {
		return opctionalEmpresa.map(empresa -> new EmpresaDTO(empresa.getId(), 
											new RutDTO(empresa.getNumeroRut(), 
											empresa.getDigitoVerificador()), 
											empresa.getRazonSocial(), 
											empresa.getFechaInsercion()))
				.orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
	}
	
	/**
	 * Metodo pasa los datos de un Entity Empresa a un DTO EmpresaDTO para enviar al consumidor
	 * @param empresa
	 * @return empresaDTO
	 */
	public static EmpresaDTO convertirAEmpresaDTO(Empresa empresa) {
		EmpresaDTO empresaDTO = new EmpresaDTO();
		empresaDTO.setId(empresa.getId());
		RutDTO rut = new RutDTO();
		rut.setNumero(empresa.getNumeroRut());
		rut.setDigitoVerificador(empresa.getDigitoVerificador());
		empresaDTO.setRut(rut);
		empresaDTO.setRazonSocial(empresa.getRazonSocial());
		empresaDTO.setFechaInsercion(empresa.getFechaInsercion());
		return empresaDTO;
	}
	
	/**
	 * Metodo pasa los datos de un DTO a una clase Entity
	 * @param trabajadorDTO Recibi una instancia de de transferencia de datos para para ser traspasar datos
	 * @return trabajador Retorna una instancia de entidad para luego ser usada por los repositorios
	 */
	public static Trabajador convertirATrabajador(TrabajadorDTO trabajadorDTO) {
		Trabajador trabajador = new Trabajador();
		trabajador.setId(trabajadorDTO.getId());
		trabajador.setNumeroRut(trabajadorDTO.getRut().getNumero());
		trabajador.setDigitoVerificador(trabajadorDTO.getRut().getDigitoVerificador());
		trabajador.setNombre(trabajadorDTO.getNombre());
		trabajador.setApellidoPaterno(trabajadorDTO.getApellidoPaterno());
		trabajador.setApellidoMaterno(trabajadorDTO.getApellidoMaterno());
		trabajador.setFechaInsercion(trabajadorDTO.getFechaInsercion());
		trabajador.setDireccion(new Direccion(trabajadorDTO.getDireccion().getCalleAvenida(),
												trabajadorDTO.getDireccion().getNumero(),
												trabajadorDTO.getDireccion().getCiudad()));
		return trabajador;
	}
	
	/**
	 * Metodo pasa los datos de un Optional Entity a un DTO para enviar al consumidor
	 * @param opctionalTrabajador Recibi una instancia Optional<Trabajador> para traspasar datos
	 * @return empresaDTO Retorna una instancia DTO para enviar al consumidor
	 */
	public static TrabajadorDTO convertirATrabajadorDTO(Optional<Trabajador> opctionalTrabajador) {
		return opctionalTrabajador.map(trabajador -> new TrabajadorDTO(trabajador.getId(), 
											new RutDTO(trabajador.getNumeroRut(), 
														trabajador.getDigitoVerificador()), 
											trabajador.getNombre(), 
											trabajador.getApellidoPaterno(), 
											trabajador.getApellidoMaterno(), 
											new DireccionDTO(trabajador.getDireccion().getCalleAvenida(), 
															trabajador.getDireccion().getNumero(),
															trabajador.getDireccion().getCiudad()),
											trabajador.getFechaInsercion(),
											trabajador.getEmpresa().getId()))
				.orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
	}
	
	/**
	 * Metodo pasa los datos de un Entity Trabajador a un DTO TrabajadorDTO para enviar al consumidor
	 * @param trabajador
	 * @return trabajadorDTO
	 */
	public static TrabajadorDTO convertirATrabajadorDTO(Trabajador trabajador) {
		TrabajadorDTO trabajadorDTO = new TrabajadorDTO();
		trabajadorDTO.setId(trabajador.getId());
		RutDTO rut = new RutDTO();
		rut.setNumero(trabajador.getNumeroRut());
		rut.setDigitoVerificador(trabajador.getDigitoVerificador());
		trabajadorDTO.setRut(rut);
		trabajadorDTO.setNombre(trabajador.getNombre());
		trabajadorDTO.setApellidoPaterno(trabajador.getApellidoPaterno());
		trabajadorDTO.setApellidoMaterno(trabajador.getApellidoMaterno());
		trabajadorDTO.setFechaInsercion(trabajador.getFechaInsercion());
		trabajadorDTO.setDireccion(new DireccionDTO(trabajador.getDireccion().getCalleAvenida(), 
													trabajador.getDireccion().getNumero(),
													trabajador.getDireccion().getCiudad()));
		trabajadorDTO.setEmpresaId(trabajador.getEmpresa().getId());
		return trabajadorDTO;
	}
}

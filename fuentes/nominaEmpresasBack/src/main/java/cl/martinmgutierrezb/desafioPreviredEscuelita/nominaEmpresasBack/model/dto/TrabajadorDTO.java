package cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.model.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Clase DTO para la exposicion de datos a consumidores de la api
 * y para armar estructura de entrada de los cuerpos de la solicitud.
 */

public class TrabajadorDTO {

	@Schema(description = "ID de trabajador [AUTOGENERADO]", example = "1")
	private Long id;
	
	@Schema(description = "Rut de trabajador")
	private RutDTO rut;
	
	@Schema(description = "Nombre del trabajador", example = "Juan")
	@NotNull(message = "El nombre no puede ser nulo")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
	private String nombre;
	
	@Schema(description = "Apellido paterno del trabajador", example = "Perez")
	@NotNull(message = "El apellido paterno no puede ser nulo")
    @Size(min = 2, max = 100, message = "El apellido paterno debe tener entre 2 y 100 caracteres")
	private String apellidoPaterno;
	
	@Schema(description = "Apellido materno del trabajador", example = "Gonzalez")
	@NotNull(message = "El apellido materno no puede ser nulo")
    @Size(min = 2, max = 100, message = "El apellido materno debe tener entre 2 y 100 caracteres")
	private String apellidoMaterno;
	
	@Schema(description = "Direccion del trabajador")
	@NotNull(message = "La direccion no puede ser nula")
	private DireccionDTO direccion;
	
	@Schema(description = "Fecha de inserción [AUTOGENERADA]")
	private LocalDate fechaInsercion;
	
	@Schema(description = "ID de la empresa a la cual pertenece")
	@NotNull(message = "El id de la empresa a la cual pertenece el trabajador no puede ser nulo")
    @Size(min = 10, max = 50, message = "El id de la empresa  debe tener entre 10 y 50 caracteres")
	private String empresaId;
	
	/**
	 * Contructor genérico
	 */
	public TrabajadorDTO() {
		super();
	}

	/**
	 * Contructor con todos los campos
	 * @param id, rut, nombre, apellidoPaterno, apellidoMaterno, direccion, fechaInsercion, empresaId
	 */
	public TrabajadorDTO(Long id, RutDTO rut, String nombre, String apellidoPaterno, String apellidoMaterno,
			DireccionDTO direccion, LocalDate fechaInsercion, String empresaId) {
		super();
		this.id = id;
		this.rut = rut;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.direccion = direccion;
		this.fechaInsercion = fechaInsercion;
		this.empresaId = empresaId;
	}



	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public RutDTO getRut() {
		return rut;
	}
	
	public void setRut(RutDTO rut) {
		this.rut = rut;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	
	public DireccionDTO getDireccion() {
		return direccion;
	}
	
	public void setDireccion(DireccionDTO direccion) {
		this.direccion = direccion;
	}
	
	public LocalDate getFechaInsercion() {
		return fechaInsercion;
	}
	
	public void setFechaInsercion(LocalDate fechaInsercion) {
		this.fechaInsercion = fechaInsercion;
	}
	
	public String getEmpresaId() {
		return empresaId;
	}

	public void setEmpresaId(String empresaId) {
		this.empresaId = empresaId;
	}

	@Override
	public String toString() {
		return "Trabajador [id=" + id + ", rut=" + rut + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno
				+ ", apellidoMaterno=" + apellidoMaterno + ", direccion=" + direccion + ", fechaInsercion="
				+ fechaInsercion + "]";
	}
	
	
	
}

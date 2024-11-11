package cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.model.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Clase DTO para la exposicion de datos a consumidores de la api
 * y para armar estructura de entrada de los cuerpos de la solicitud.
 */
public class EmpresaDTO {
	
	@Schema(description = "ID de la empresa [AUTOGENERADO]", example = "15641561654Kf4es564")
	private String id;
	
	@Schema(description = "Rut de la empresa")
	@NotNull(message = "El rut no puede ser nulo")
	private RutDTO rut;
	
	@Schema(description = "Razon Social de la empresa", example = "Inversiones GA")
	@NotNull(message = "El rur no puede ser nulo")
	@Size(min = 2, max = 100, message = "La razon social debe tener entre 2 y 100 caracteres")
	private String razonSocial;
	
	@Schema(description = "Fecha de inserción [AUTOGENERADA]")
	private LocalDate fechaInsercion;
	
	/**
	 * Contructor genérico
	 */
	public EmpresaDTO() {
		super();
	}

	/**
	 * Contructor con todos los campos
	 * @param id, rut, razonSocial, fechaInsercion
	 */
	public EmpresaDTO(String id, RutDTO rut, String razonSocial, LocalDate fechaInsercion) {
		super();
		this.id = id;
		this.rut = rut;
		this.razonSocial = razonSocial;
		this.fechaInsercion = fechaInsercion;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public RutDTO getRut() {
		return rut;
	}
	
	public void setRut(RutDTO rut) {
		this.rut = rut;
	}
	
	public String getRazonSocial() {
		return razonSocial;
	}
	
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	
	public LocalDate getFechaInsercion() {
		return fechaInsercion;
	}
	
	public void setFechaInsercion(LocalDate fechaInsercion) {
		this.fechaInsercion = fechaInsercion;
	}
	
	@Override
	public String toString() {
		return "Empresa [id=" + id + ", rut=" + rut + ", razonSocial=" + razonSocial + ", fechaInsercion="
				+ fechaInsercion + "]";
	}
}

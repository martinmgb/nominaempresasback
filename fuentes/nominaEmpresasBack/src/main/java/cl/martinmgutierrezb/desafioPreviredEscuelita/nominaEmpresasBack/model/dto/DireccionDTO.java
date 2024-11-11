package cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Clase DTO para la exposicion de datos a consumidores de la api
 * y para armar estructura de entrada de los cuerpos de la solicitud.
 */
public class DireccionDTO {
	
	@NotNull(message = "La calleAvenida no puede ser nula")
	@Size(min = 1, max = 100, message = "La calleAvenida debe tener entre [1-100] caracteres")
	private String calleAvenida;
	
	@NotNull(message = "El numero de direccion no puede ser nulo")
	@Size(min = 1, max = 10, message = "El numero de direccion debe tener entre [1-10] caracteres")
	private String numero;
	
	@NotNull(message = "La ciudad no puede ser nula")
	@Size(min = 1, max = 40, message = "La calleAvenida debe tener entre [1-40] caracteres")
	private String ciudad;
	
	/**
	 * Contructor con todos los campos
	 */
	public DireccionDTO(String calleAvenida, String numero, String ciudad) {
		super();
		this.calleAvenida = calleAvenida;
		this.numero = numero;
		this.ciudad = ciudad;
	}

	public String getCalleAvenida() {
		return calleAvenida;
	}
	
	public void setCalleAvenida(String calleAvenida) {
		this.calleAvenida = calleAvenida;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getCiudad() {
		return ciudad;
	}
	
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	@Override
	public String toString() {
		return "DireccionDTO [calleAvenida=" + calleAvenida + ", numero=" + numero + ", ciudad=" + ciudad + "]";
	}
	
	
	
	
}

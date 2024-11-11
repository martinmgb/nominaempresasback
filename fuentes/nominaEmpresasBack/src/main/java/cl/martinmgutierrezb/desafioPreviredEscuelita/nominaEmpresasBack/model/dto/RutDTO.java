package cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.model.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Clase DTO para la exposicion de datos a consumidores de la api
 * y para armar estructura de entrada de los cuerpos de la solicitud.
 */
public class RutDTO {
	
	@Schema(description = "Número de rut de la persona", example = "77667797")
	@Min(value = 1, message = "El rut debe ser mayor a cero")
    @Max(value = 999999999, message = "El rut no tiene la longitud esperada, debe tener un máximo de 9 dígitos")
	private Long numero;
	
	@Schema(description = "Digito verificador del rut de la persona", example = "K")
	@Pattern(regexp = "^[kK0-9]{1}$", message = "Debe ser un único carácter alfanumérico con caraceters [kK0-9]")
	private String digitoVerificador;

	/**
	 * Contructor genérico
	 */
	public RutDTO() {
		super();
	}

	/**
	 * Contructor con todos los campos
	 * @param numero, digitoVerificador
	 * Adicionalmente se sube a mayusculas el digito verificador 
	 * para que se hayen datos como 'k' y 'K'.
	 */
	public RutDTO(Long numero, String digitoVerificador) {
		super();
		this.numero = numero;
		this.digitoVerificador = digitoVerificador.toUpperCase();
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getDigitoVerificador() {
		return digitoVerificador;
	}

	public void setDigitoVerificador(String digitoVerificador) {
		this.digitoVerificador = digitoVerificador.toUpperCase();
	}

	@Override
	public String toString() {
		return "Rut [numero=" + numero + ", digitoVerificador=" + digitoVerificador + "]";
	}
	
}

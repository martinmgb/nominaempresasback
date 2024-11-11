package cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "direcciones")
public class Direccion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "calle_avenida", nullable = false) 
	private String calleAvenida;
	
	@Column(name = "numero", nullable = false) 
	private String numero;
	
	@Column(name = "ciudad", nullable = false) 
	private String ciudad;
	
	public Direccion() {
		super();
	}
	

	public Direccion(String calleAvenida, String numero, String ciudad) {
		super();
		this.calleAvenida = calleAvenida;
		this.numero = numero;
		this.ciudad = ciudad;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		return "Direccion [calleAvenida=" + calleAvenida + ", numero=" + numero   + ", ciudad=" + ciudad + "]";
	}
	
	
}

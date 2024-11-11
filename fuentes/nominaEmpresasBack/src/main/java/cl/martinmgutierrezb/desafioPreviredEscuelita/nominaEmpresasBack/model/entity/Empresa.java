package cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.model.entity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.util.UtilIdentificador;

@Entity
@Table(name = "empresas")
public class Empresa {
	
	@Id
	@Column(name = "id", nullable = false, unique = true) 
	private String id;
	
	@Column(name = "numero_rut", nullable = false, unique = true)
	private Long numeroRut;
	
	@Column(name = "digito_verificador", nullable = false) 
	private String digitoVerificador;
	
	@Column(name = "razon_social", nullable = false) 
	private String razonSocial;
	
	@Column(name = "fecha_insercion", nullable = false) 
	private LocalDate fechaInsercion;
	
	@OneToMany(mappedBy = "empresa", cascade = CascadeType.REMOVE)
	private List<Trabajador> trabajadores;
	
	@PrePersist
	public void prePersist() {
		this.fechaInsercion = LocalDate.now(ZoneId.of("America/Santiago"));
		this.id = UtilIdentificador.crearIdentificadorEmpresa(razonSocial, numeroRut, digitoVerificador);
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public Long getNumeroRut() {
		return numeroRut;
	}

	public void setNumeroRut(Long numeroRut) {
		this.numeroRut = numeroRut;
	}

	public String getDigitoVerificador() {
		return digitoVerificador;
	}

	public void setDigitoVerificador(String digitoVerificador) {
		this.digitoVerificador = digitoVerificador;
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

	public List<Trabajador> getTrabajadores() {
		return trabajadores;
	}

	public void setTrabajadores(List<Trabajador> trabajadores) {
		this.trabajadores = trabajadores;
	}

	@Override
	public String toString() {
		return "Empresa [id=" + id + ", numeroRut=" + numeroRut + ", digitoVerificador=" + digitoVerificador
				+ ", razonSocial=" + razonSocial + ", fechaInsercion=" + fechaInsercion + "]";
	}

	
	

}

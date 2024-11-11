package cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.model.entity;

import java.time.LocalDate;
import java.time.ZoneId;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "trabajadores")
public class Trabajador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "numero_rut", nullable = false, unique = true)
	private Long numeroRut;
	
	@Column(name = "digito_verificador", nullable = false) 
	private String digitoVerificador;
	
	@Column(name = "nombre", nullable = false) 
	private String nombre;
	
	@Column(name = "apellido_paterno", nullable = false) 
	private String apellidoPaterno;
	
	@Column(name = "apellido_materno") 
	private String apellidoMaterno;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "direccion_id") 
	private Direccion direccion;
	
	@Column(name = "fecha_insercion") 
	private LocalDate fechaInsercion;
	
	@ManyToOne
    @JoinColumn(name = "empresa_id", nullable = false)
	private Empresa empresa;
	
	@PrePersist
	public void prePersist() {
		this.fechaInsercion = LocalDate.now(ZoneId.of("America/Santiago"));
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
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

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public LocalDate getFechaInsercion() {
		return fechaInsercion;
	}
	
	public void setFechaInsercion(LocalDate fechaInsercion) {
		this.fechaInsercion = fechaInsercion;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}	
	
	
	
}

package com.cibertec.edu.entities;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "empleado")
public class Empleado implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_emp")
	private int id;

	@Column(name = "nom_emp")
	@NotEmpty(message = "Campo obligatorio")
	private String nombre;

	@Column(name = "ape_emp")
	@NotEmpty(message = "Campo obligatorio")
	private String apellido;

	@Column(name = "cel_emp")
	@NotEmpty(message = "Campo obligatorio")
	private String celular;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "fec_contrato")
	@NotNull(message = "Campo obligatorio")
	private Date fecContrato;

	@Column(name = "cargo_emp")
	@NotEmpty(message = "Campo obligatorio")
	private String cargo;

	@Column(name = "suel_emp")
	@NotNull(message = "Campo obligatorio")
	private double sueldo;

	@Column(name = "cor_emp")
	@NotEmpty(message = "Campo obligatorio")
	private String correo;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_usu")
	@NotNull(message = "Campo obligatorio")
	private Usuario usuario;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public Date getFecContrato() {
		return fecContrato;
	}

	public void setFecContrato(Date fecContrato) {
		this.fecContrato = fecContrato;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public double getSueldo() {
		return sueldo;
	}

	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", celular=" + celular
				+ ", fecContrato=" + fecContrato + ", cargo=" + cargo + ", sueldo=" + sueldo + ", correo=" + correo
				+ ", usuario=" + usuario + "]";
	}

	private static final long serialVersionUID = 1L;
}

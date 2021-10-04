package com.ias.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tecnicos")
public class Tecnico implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Column(nullable = false)
	private String nombre;
	
	@NotEmpty
	private String apellido;
	
	@NotEmpty
	@Size(min=10, max=10)
	private String telefono;
	
	@NotEmpty
	@Email
	@Column(nullable = false, unique = true)
	private String email;
	
	@JsonIgnoreProperties({"tecnico", "hibernateLazyInitializer", "handler"})
	@OneToMany(fetch =FetchType.LAZY, mappedBy = "tecnico", cascade = CascadeType.ALL)
	private List<Servicio> servicio;
	
	
	
	public Tecnico() {
		this.servicio = new ArrayList<>();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
	public List<Servicio> getServicio() {
		return servicio;
	}
	public void setServicio(List<Servicio> servicio) {
		this.servicio = servicio;
	}


	private static final long serialVersionUID = 1L;
	
	
}

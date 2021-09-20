package com.ias.springboot.backend.apirest.models.services;

import java.util.List;

import com.ias.springboot.backend.apirest.models.entity.Tecnico;

public interface ITecnicoService {
	
	public List<Tecnico> findAll();
	
	public Tecnico findById(Long id);

	public Tecnico save(Tecnico tecnico);
	
	public void delete(Long id);
	
	
}

package com.ias.springboot.backend.apirest.models.services;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ias.springboot.backend.apirest.models.entity.Servicio;
import com.ias.springboot.backend.apirest.models.entity.Tecnico;

public interface ITecnicoService {
	
	public List<Tecnico> findAll();
	
	public Page<Tecnico> findAll(Pageable pegeable);
	
	public Tecnico findById(Long id);

	public Tecnico save(Tecnico tecnico);
	
	public void delete(Long id);
	
	public Servicio findFServicioById(Long id);
	
	public Servicio saveServicio(Servicio servicio);
	
	public void deleteServicioById(Long id);
}

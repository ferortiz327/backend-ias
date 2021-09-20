package com.ias.springboot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.ias.springboot.backend.apirest.models.entity.Tecnico;

public interface ITecnicoDao extends CrudRepository<Tecnico, Long>{

	
}

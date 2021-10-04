package com.ias.springboot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.ias.springboot.backend.apirest.models.entity.Servicio;

public interface IServiceDao extends CrudRepository<Servicio, Long>{

}

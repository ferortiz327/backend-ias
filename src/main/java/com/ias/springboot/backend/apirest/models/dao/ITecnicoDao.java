package com.ias.springboot.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ias.springboot.backend.apirest.models.entity.Tecnico;

public interface ITecnicoDao extends JpaRepository<Tecnico, Long>{

}

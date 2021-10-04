package com.ias.springboot.backend.apirest.models.services;
import org.springframework.data.domain.Pageable;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ias.springboot.backend.apirest.models.dao.IServiceDao;
import com.ias.springboot.backend.apirest.models.dao.ITecnicoDao;
import com.ias.springboot.backend.apirest.models.entity.Servicio;
import com.ias.springboot.backend.apirest.models.entity.Tecnico;

@Service
public class TecnicoServiceImpl implements ITecnicoService{

	@Autowired
	private ITecnicoDao tecnicoDao;
	
	@Autowired
	private IServiceDao servicioDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Tecnico> findAll() {
		
		return (List<Tecnico>) tecnicoDao.findAll();
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public Tecnico findById(Long id) {
		return tecnicoDao.findById(id).orElse(null);
	}
	
	
	@Override
	@Transactional
	public Tecnico save(Tecnico tecnico) {
		return tecnicoDao.save(tecnico);
	}
	
	
	@Override
	@Transactional
	public void delete(Long id) {
		tecnicoDao.deleteById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Page<Tecnico> findAll(Pageable pegeable) {
		return tecnicoDao.findAll(pegeable);
	}


	@Override
	@Transactional(readOnly = true)
	public Servicio findFServicioById(Long id) {
		return servicioDao.findById(id).orElse(null);
	}


	@Override
	@Transactional
	public Servicio saveServicio(Servicio servicio) {
		return servicioDao.save(servicio);
	}

	@Override
	@Transactional
	public void deleteServicioById(Long id) {
		servicioDao.deleteById(id);
	}


}

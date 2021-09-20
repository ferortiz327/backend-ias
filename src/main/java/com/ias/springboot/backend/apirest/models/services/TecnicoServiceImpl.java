package com.ias.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ias.springboot.backend.apirest.models.dao.ITecnicoDao;
import com.ias.springboot.backend.apirest.models.entity.Tecnico;

@Service
public class TecnicoServiceImpl implements ITecnicoService{

	@Autowired
	private ITecnicoDao tecnicoDao;
	
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

}

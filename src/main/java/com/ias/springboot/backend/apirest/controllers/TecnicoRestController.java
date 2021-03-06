package com.ias.springboot.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ias.springboot.backend.apirest.models.entity.Tecnico;
import com.ias.springboot.backend.apirest.models.services.ITecnicoService;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class TecnicoRestController {
	
	@Autowired
	private ITecnicoService tecnicoService;
	
	@GetMapping("/tecnicos")
	public List<Tecnico> index(){
		return tecnicoService.findAll();
	}
	
	@GetMapping("/tecnicos/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Tecnico tecnico = null;
		Map<String, Object> response = new HashMap<>();
		try {
		tecnico = tecnicoService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(tecnico == null) {
			response.put("mensaje", "El técnico ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Tecnico>(tecnico, HttpStatus.OK);
	}
	
	@PostMapping("/tecnicos")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@RequestBody Tecnico tecnico) {
		
		Tecnico tecnicoNew = null;
		Map<String, Object> response = new HashMap<>();
		try {
			tecnicoNew = tecnicoService.save(tecnico);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al crear el técnico");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El Técnico ha sido creado con exito");
		response.put("Técnico", tecnicoNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/tecnicos/{id}")
	public ResponseEntity<?> update(@RequestBody Tecnico tecnico, @PathVariable Long id) {
		Tecnico tecnicoActual = tecnicoService.findById(id);
		Tecnico tecnicoUpdate = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(tecnicoActual == null) {
			response.put("mensaje", "Error, no se puede editar el técnico ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			tecnicoActual.setApellido(tecnico.getApellido());
			tecnicoActual.setNombre(tecnico.getNombre());
			tecnicoActual.setTelefono(tecnico.getTelefono());
			tecnicoActual.setEmail(tecnico.getEmail());
			tecnicoUpdate = tecnicoService.save(tecnicoActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al editar el técnico");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El Técnico ha sido editado con éxito");
		response.put("Técnico", tecnicoUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/tecnicos/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			tecnicoService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el técnico");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El técnico ha sido elimidado con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}

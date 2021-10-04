package com.ias.springboot.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ias.springboot.backend.apirest.models.entity.Servicio;
import com.ias.springboot.backend.apirest.models.entity.Tecnico;
import com.ias.springboot.backend.apirest.models.services.ITecnicoService;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class ServicioRestController {

	@Autowired
	private ITecnicoService tecnicoService;
	
	@GetMapping("/servicios/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Servicio show(@PathVariable Long id) {
		return tecnicoService.findFServicioById(id);
	}
	
	@DeleteMapping("/servicios/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		tecnicoService.deleteServicioById(id);
	}
	
	@PostMapping("/servicios")
	public ResponseEntity<?> create(@Valid @RequestBody Servicio servicio, BindingResult result) {
		
		Servicio servicioNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errores", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			
		}
	
		try {
			servicioNew = tecnicoService.saveServicio(servicio);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al crear el técnico");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El Técnico ha sido creado con exito");
		response.put("Técnico", servicioNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}

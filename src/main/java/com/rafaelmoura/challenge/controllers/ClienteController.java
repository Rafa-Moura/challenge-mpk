package com.rafaelmoura.challenge.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rafaelmoura.challenge.models.Cliente;
import com.rafaelmoura.challenge.services.ClienteService;

@RestController
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<Cliente>> listAll() {
		return clienteService.findAll();
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<?> listOne(@PathVariable Long id) {
		return clienteService.findById(id);
	}

	@PostMapping("/")
	public ResponseEntity<?> saveCliente(@Valid @RequestBody Cliente cliente) {
		return clienteService.saveCliente(cliente);
	}

	@DeleteMapping(value = "/cliente/{id}")
	public ResponseEntity<?> deleteCliente(@PathVariable Long id) {
		return clienteService.deleteCliente(id);
	}

	@PutMapping(value = "/atualizar/{id}")
	public ResponseEntity<?> updateCliente(@Valid @RequestBody Cliente cliente, @PathVariable Long id) {
		return clienteService.updateCliente(cliente, id);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
}

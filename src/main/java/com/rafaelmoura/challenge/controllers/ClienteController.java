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

import com.rafaelmoura.challenge.exceptions.ClienteNotFoundException;
import com.rafaelmoura.challenge.exceptions.CnpjExistenteException;
import com.rafaelmoura.challenge.models.Cliente;
import com.rafaelmoura.challenge.services.ClienteService;

@RestController
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	@GetMapping(value = "/", produces = "application/json")
	public List<Cliente> listAll() {
		return clienteService.findAll();
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<?> listOne(@PathVariable Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(clienteService.findById(id));
		} catch (ClienteNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
		}

	}

	@PostMapping("/")
	public ResponseEntity<?> saveCliente(@Valid @RequestBody Cliente cliente) {
		try {
			clienteService.saveCliente(cliente);
			return ResponseEntity.status(HttpStatus.OK).body("Cliente cadastrado com sucesso");
		} catch (CnpjExistenteException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CNPJ já existe no sistema");
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteCliente(@PathVariable Long id) {
		try {
			clienteService.deleteCliente(id);
			return ResponseEntity.status(HttpStatus.OK).body("Cliente excluido com sucesso");
		} catch (ClienteNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
		}
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateCliente(@Valid @RequestBody Cliente cliente, @PathVariable Long id) {

		try {
			clienteService.updateCliente(cliente, id);
			return ResponseEntity.status(HttpStatus.OK).body("Cliente atualizado com sucesso");

		} catch (ClienteNotFoundException | CnpjExistenteException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente já cadastrado ou não encontrado");
		}

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

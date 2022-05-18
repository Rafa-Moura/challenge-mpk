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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rafaelmoura.challenge.dto.ClienteDTO;
import com.rafaelmoura.challenge.dto.ClienteInsertDTO;
import com.rafaelmoura.challenge.exceptions.ClienteNotFoundException;
import com.rafaelmoura.challenge.exceptions.CnpjExistenteException;
import com.rafaelmoura.challenge.services.ClienteServiceImpl;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

	private static final String ERROR_MSG_NOTFOUND = "Cliente não encontrado";

	@Autowired
	ClienteServiceImpl clienteService;

	@GetMapping(produces = "application/json")
	public List<ClienteDTO> listAll() {
		return clienteService.findAll();
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<?> listOne(@PathVariable Long id) {
		try {
			ClienteDTO cliente = clienteService.findById(id);
			return ResponseEntity.status(HttpStatus.OK).body(cliente);
		} catch (ClienteNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ERROR_MSG_NOTFOUND);
		}

	}

	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody ClienteInsertDTO cliente) {
		try {
			ClienteDTO clienteDto = clienteService.save(cliente);
			return ResponseEntity.status(HttpStatus.CREATED).body(clienteDto);
		} catch (CnpjExistenteException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CNPJ já existe no sistema");
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			clienteService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Cliente excluido com sucesso");
		} catch (ClienteNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ERROR_MSG_NOTFOUND);
		}
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody ClienteDTO dto, @PathVariable Long id) {
		try {
			clienteService.update(dto, id);
			return ResponseEntity.status(HttpStatus.OK).body("Cliente atualizado com sucesso");
		} catch (ClienteNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ERROR_MSG_NOTFOUND);
		} catch (CnpjExistenteException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CNPJ já cadastrado");
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

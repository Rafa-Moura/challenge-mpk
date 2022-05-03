package com.rafaelmoura.challenge.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public ResponseEntity<?> saveCliente(@RequestBody Cliente cliente) {
		return clienteService.saveCliente(cliente);
	}

	@DeleteMapping(value = "/cliente/{id}")
	public void deleteCliente(@PathVariable Long id) {
		clienteService.deleteCliente(id);
	}

	@PutMapping(value = "/atualizar/{id}")
	public ResponseEntity<?> updateCliente(@RequestBody Cliente cliente, @PathVariable Long id) {
		return clienteService.updateCliente(cliente, id);
	}

	@GetMapping(value = "/cliente/{cnpj}", produces = "application/json")
	public ResponseEntity<?> findByCnpj(@PathVariable String cnpj) {
		return clienteService.findByCnpj(cnpj);
	}

}

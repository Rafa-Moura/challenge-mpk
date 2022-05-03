package com.rafaelmoura.challenge.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rafaelmoura.challenge.models.Cliente;
import com.rafaelmoura.challenge.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	public ResponseEntity<?> saveCliente(Cliente cliente) {

		Optional<Cliente> clienteAtual = clienteRepository.findByCnpj(cliente.getCnpj());

		if (cliente.getIdade() < 18) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Cliente não pode ser menor de 18 anos");
		}

		if (!clienteAtual.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("CNPJ já cadastrado");
		}

		Cliente clienteSalvo = clienteRepository.save(cliente);

		new ResponseEntity<Cliente>(clienteSalvo, HttpStatus.OK);
		return ResponseEntity.status(HttpStatus.OK).body("Cliente cadastrado com sucesso");
	}

	public ResponseEntity<List<Cliente>> findAll() {
		List<Cliente> list = (List<Cliente>) clienteRepository.findAll();

		return new ResponseEntity<List<Cliente>>(list, HttpStatus.OK);
	}

	public ResponseEntity<?> findById(Long id) {

		Optional<Cliente> cliente = clienteRepository.findById(id);

		if (!cliente.isPresent()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Cliente não encontrado");
		}
		return new ResponseEntity<Cliente>(cliente.get(), HttpStatus.OK);
	}

	public ResponseEntity<?> updateCliente(Cliente cliente, Long id) {
		Optional<Cliente> clienteSalvo = clienteRepository.findById(id);
		if (clienteSalvo.isPresent()) {
			Cliente user = clienteRepository.save(cliente);
			return ResponseEntity.ok(user);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
		}
	}

	public void deleteCliente(Long id) {
		clienteRepository.deleteById(id);
	}

	public ResponseEntity<?> findByCnpj(String cnpj) {

		Optional<Cliente> cliente = clienteRepository.findByCnpj(cnpj);

		if (!cliente.isPresent()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Cliente não encontrado");
		}
		return new ResponseEntity<Cliente>(cliente.get(), HttpStatus.OK);
	}
}

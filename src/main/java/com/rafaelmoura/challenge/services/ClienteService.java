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
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente não pode ser menor de 18 anos");
		}

		if (!clienteAtual.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CNPJ já cadastrado");
		}

		if (cliente.getCnpj().equals("")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CNPJ Não pode ser nulo");
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
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
		}
		return new ResponseEntity<Cliente>(cliente.get(), HttpStatus.OK);
	}

	public ResponseEntity<?> updateCliente(Cliente cliente, Long id) {
		Optional<Cliente> clienteSalvo = clienteRepository.findById(id);

		Optional<Cliente> checarCnpj = clienteRepository.findByCnpj(cliente.getCnpj());

		if (clienteSalvo.isPresent()) {
			if (!checarCnpj.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Este CNPJ já consta em nossos registros!");
			} else {
				Cliente clienteAtual = clienteRepository.getById(id);
				cliente.setId(clienteAtual.getId());
				Cliente user = clienteRepository.save(cliente);
				return ResponseEntity.ok(user);
			}
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
		}
	}

	public ResponseEntity<?> deleteCliente(Long id) {
		Optional<Cliente> checarCliente = clienteRepository.findById(id);

		if (!checarCliente.isEmpty()) {
			clienteRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Cliente Excluido com sucesso");
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
	}

}

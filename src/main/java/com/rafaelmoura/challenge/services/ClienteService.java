package com.rafaelmoura.challenge.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafaelmoura.challenge.exceptions.ClienteNotFoundException;
import com.rafaelmoura.challenge.exceptions.CnpjExistenteException;
import com.rafaelmoura.challenge.models.Cliente;
import com.rafaelmoura.challenge.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	public Cliente saveCliente(Cliente cliente) throws CnpjExistenteException {

		Optional<Cliente> clienteAtual = clienteRepository.findByCnpj(cliente.getCnpj());

		if (!clienteAtual.isEmpty()) {
			throw new CnpjExistenteException(cliente.getCnpj());
		}

		return clienteRepository.save(cliente);
	}

	public List<Cliente> findAll() {
		List<Cliente> list = (List<Cliente>) clienteRepository.findAll();

		return list;
	}

	public Cliente findById(Long id) throws ClienteNotFoundException {

		Optional<Cliente> cliente = clienteRepository.findById(id);

		if (!cliente.isPresent()) {
			throw new ClienteNotFoundException(id);
		}
		return cliente.get();
	}

	public Cliente updateCliente(Cliente cliente, Long id) throws ClienteNotFoundException, CnpjExistenteException {
		Optional<Cliente> clienteSalvo = clienteRepository.findById(id);

		Optional<Cliente> checarCnpj = clienteRepository.findByCnpj(cliente.getCnpj());

		if (clienteSalvo.isPresent()) {
			if (!checarCnpj.isEmpty()) {
				throw new CnpjExistenteException(cliente.getCnpj());
			} else {
				Cliente clienteAtual = clienteRepository.getById(id);
				cliente.setId(clienteAtual.getId());
				return clienteRepository.save(cliente);
			}
		} else {
			throw new ClienteNotFoundException(cliente.getId());
		}
	}

	public void deleteCliente(Long id) throws ClienteNotFoundException {

		Optional<Cliente> checarCliente = clienteRepository.findById(id);

		if (!checarCliente.isEmpty()) {
			clienteRepository.deleteById(id);
		} else {
			throw new ClienteNotFoundException(id);
		}
	}

}

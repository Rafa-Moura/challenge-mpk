package com.rafaelmoura.challenge.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafaelmoura.challenge.dto.ClienteDTO;
import com.rafaelmoura.challenge.dto.ClienteInsertDTO;
import com.rafaelmoura.challenge.exceptions.ClienteNotFoundException;
import com.rafaelmoura.challenge.exceptions.CnpjExistenteException;
import com.rafaelmoura.challenge.models.Cliente;
import com.rafaelmoura.challenge.repositories.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public ClienteDTO save(ClienteInsertDTO dto) throws CnpjExistenteException {

		Cliente clienteAtual = clienteRepository.findByCnpj(dto.getCnpj());

		if (clienteAtual != null) {
			throw new CnpjExistenteException(dto.getCnpj());
		}

		Cliente cliente = new Cliente();
		cliente.setNome(dto.getNome());
		cliente.setCnpj(dto.getCnpj());
		cliente.setIdade(dto.getIdade());
		cliente.setDataNascimento(dto.getDataNascimento());

		cliente = clienteRepository.save(cliente);

		return new ClienteDTO(cliente);
	}

	public List<ClienteDTO> findAll() {
		List<Cliente> list = clienteRepository.findAll();
		return list.stream().map(result -> new ClienteDTO(result)).collect(Collectors.toList());
	}

	public ClienteDTO findById(Long id) throws ClienteNotFoundException {

		Optional<Cliente> cliente = clienteRepository.findById(id);

		if (!cliente.isPresent()) {
			throw new ClienteNotFoundException(id);
		} else {
			Cliente clienteLocalizado = cliente.get();
			ClienteDTO clienteDto = new ClienteDTO();
			clienteDto.setId(clienteLocalizado.getId());
			clienteDto.setNome(clienteLocalizado.getNome());
			clienteDto.setIdade(clienteLocalizado.getIdade());
			clienteDto.setCnpj(clienteLocalizado.getCnpj());
			clienteDto.setDataNascimento(clienteLocalizado.getDataNascimento());
			return clienteDto;
		}
	}

	public ClienteDTO update(ClienteDTO dto, Long id) throws ClienteNotFoundException, CnpjExistenteException {

		Optional<Cliente> cnpj = Optional.ofNullable(clienteRepository.findByCnpj(dto.getCnpj()));

		boolean result = clienteNotFound(id);

		if (result) {
			throw new ClienteNotFoundException(id);
		}

		if (!cnpj.isEmpty()) {
			throw new CnpjExistenteException(dto.getCnpj());
		} else {
			Cliente cliente = new Cliente();
			cliente = clienteRepository.getById(id);
			cliente.setId(id);
			cliente.setNome(dto.getNome());
			cliente.setCnpj(dto.getCnpj());
			cliente.setIdade(dto.getIdade());
			cliente.setDataNascimento(dto.getDataNascimento());
			cliente = clienteRepository.save(cliente);

			return new ClienteDTO(cliente);
		}
	}

	public void delete(Long id) throws ClienteNotFoundException {

		Optional<Cliente> cliente = clienteRepository.findById(id);

		if (cliente.isPresent()) {
			clienteRepository.deleteById(id);
		} else {
			throw new ClienteNotFoundException(id);
		}
	}

	private boolean clienteNotFound(Long id) throws ClienteNotFoundException {

		Optional<Cliente> cliente = clienteRepository.findById(id);

		if (cliente.isPresent()) {
			return false;
		} else {
			return true;
		}
	}
}

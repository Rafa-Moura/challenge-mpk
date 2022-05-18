package com.rafaelmoura.challenge.services;

import java.util.List;

import com.rafaelmoura.challenge.dto.ClienteDTO;
import com.rafaelmoura.challenge.dto.ClienteInsertDTO;
import com.rafaelmoura.challenge.exceptions.ClienteNotFoundException;
import com.rafaelmoura.challenge.exceptions.CnpjExistenteException;

public interface ClienteService {

	public ClienteDTO save(ClienteInsertDTO dto) throws CnpjExistenteException;

	public List<ClienteDTO> findAll();

	public ClienteDTO findById(Long id) throws ClienteNotFoundException;

	public ClienteDTO update(ClienteDTO dto, Long id) throws ClienteNotFoundException, CnpjExistenteException;

	public void delete(Long id) throws ClienteNotFoundException;

}

package com.rafaelmoura.challenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafaelmoura.challenge.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	public Cliente findByCnpj(String cnpj);
}

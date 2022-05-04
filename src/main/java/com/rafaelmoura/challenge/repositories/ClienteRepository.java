package com.rafaelmoura.challenge.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafaelmoura.challenge.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	public Optional<Cliente> findByCnpj(String cnpj);
}

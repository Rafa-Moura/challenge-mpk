package com.rafaelmoura.challenge.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rafaelmoura.challenge.models.Cliente;

public class ClienteDTO {

	private Long id;
	private String nome;
	private String cnpj;
	private Integer idade;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date dataNascimento;

	public ClienteDTO() {

	}

	public ClienteDTO(Long id, String nome, String cnpj, Integer idade, Date dataNascimento) {
		super();
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
		this.idade = idade;
		this.dataNascimento = dataNascimento;
	}

	public ClienteDTO(Cliente cliente) {
		super();
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.cnpj = cliente.getCnpj();
		this.idade = cliente.getIdade();
		this.dataNascimento = cliente.getDataNascimento();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}

package com.rafaelmoura.challenge.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rafaelmoura.challenge.models.Cliente;

public class ClienteInsertDTO {

	private String nome;
	private String cnpj;
	private Integer idade;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date dataNascimento;

	public ClienteInsertDTO() {

	}

	public ClienteInsertDTO(String nome, String cnpj, Integer idade, Date dataNascimento) {
		super();
		this.nome = nome;
		this.cnpj = cnpj;
		this.idade = idade;
		this.dataNascimento = dataNascimento;
	}

	public ClienteInsertDTO(Cliente cliente) {
		super();
		this.nome = cliente.getNome();
		this.cnpj = cliente.getCnpj();
		this.idade = cliente.getIdade();
		this.dataNascimento = cliente.getDataNascimento();
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

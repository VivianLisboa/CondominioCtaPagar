package br.com.vivian.condominioctapagar.dto;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CNPJ;

public class CondominioDTO {
	@NotNull
	@NotEmpty
	@Id
	private Integer id;

	@NotNull
	@NotEmpty
	private String nome;

	@NotNull
	@NotEmpty
	@CNPJ
	private String cnpj;

	private String contato;

	public Integer getId() {
		return id;
	}
	

	public void setId(Integer id) {
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

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

}

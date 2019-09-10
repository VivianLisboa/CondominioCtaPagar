package br.com.vivian.condominioctapagar.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CNPJ;


@Entity
@Table(name = "condominio")
public class Condominio extends BaseDominio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_condominio")
	private Integer id;

	@NotNull(message = "Nome não pode se nulo")
	@NotEmpty(message = "Nome não pode se vazio")
	private String nome;

	@NotNull(message = "CNPJ não pode ser nulo")
	@NotEmpty(message = "CNPJ não pode ser vazio")
	@CNPJ
	private String cnpj;

	private String contato;

	private Condominio() {
	}

	public Condominio(String nome, String cnpj, String contato) {
		this.nome = nome;
		this.cnpj = cnpj;
		this.contato = contato;
		validarDominio();
	}

	public Condominio(Integer id,String nome,String cnpj,String contato) {
		this(nome,cnpj,contato);
		this.id = id;
		
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public String getContato() {
		return contato;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Condominio other = (Condominio) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		return true;
	}
	
	
}

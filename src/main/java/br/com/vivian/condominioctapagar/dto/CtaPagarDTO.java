package br.com.vivian.condominioctapagar.dto;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.vivian.condominioctapagar.domain.Condominio;

public class CtaPagarDTO {

	@Id
	
	private Integer id;

	@NotNull
	private Condominio condominio;

	@NotNull

	private String data;

	@NotNull

	private String historico;

	@NotNull
	private Double debito;

	@NotNull
	private Double credito;

	@NotNull
	private Double saldo;

	private String observacao;
	private String docValido;
	private String comprovPgto;
	private String pendente;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Condominio getCondominio() {
		return condominio;
	}

	public void setCondominio(Condominio condominio) {
		this.condominio = condominio;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public Double getDebito() {
		return debito;
	}

	public void setDebito(Double debito) {
		this.debito = debito;
	}

	public Double getCredito() {
		return credito;
	}

	public void setCredito(Double credito) {
		this.credito = credito;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getDocValido() {
		return docValido;
	}

	public void setDocValido(String docValido) {
		this.docValido = docValido;
	}

	public String getComprovPgto() {
		return comprovPgto;
	}

	public void setComprovPgto(String comprovPgto) {
		this.comprovPgto = comprovPgto;
	}

	public String getPendente() {
		return pendente;
	}

	public void setPendente(String pendente) {
		this.pendente = pendente;
	}

}

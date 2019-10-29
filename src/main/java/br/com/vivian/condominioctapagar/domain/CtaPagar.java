package br.com.vivian.condominioctapagar.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cta_pagar")
public class CtaPagar extends BaseDominio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cta_pagar")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_condominio")
	private Condominio condominio;

	@NotNull
	@Column
	private String data;

	@NotNull
	@Column
	private String historico;

	@NotNull
	@Column(columnDefinition = "decimal")
	private Double debito;

	@NotNull
	@Column(columnDefinition = "decimal")
	private Double credito;

	@NotNull
	@Column(columnDefinition = "decimal")
	private Double saldo;

	@Column
	private String observacao;
	@Column(name = "doc_valido")
	private String docValido;
	@Column(name = "comprov_pgto")
	private String comprovPgto;
	@Column
	private String pendente;

	public CtaPagar() {

	}

	public CtaPagar(Condominio condominio, String data, String historico, Double debito, Double credito, Double saldo) {

		this.condominio = condominio;
		this.data = data;
		this.historico = historico;
		this.debito = debito;
		this.credito = credito;
		this.saldo = saldo;
		validarDominio();
	}

	public CtaPagar(Integer id, Condominio condominio, String data, String historico, Double debito, Double credito,
			Double saldo) {
		this(condominio, data, historico, debito, credito, saldo);
		this.id = id;

	}

	public CtaPagar(Integer id, Condominio condominio, String data, String historico, Double debito, Double credito,
			Double saldo, String observacao, String docValido, String comprovPgto, String pendente) {

		this(id, condominio, data, historico, debito, credito, saldo);
		this.observacao = observacao;
		this.docValido = docValido;
		this.comprovPgto = comprovPgto;
		this.pendente = pendente;
	}

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

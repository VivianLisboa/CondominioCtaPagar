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

	private CtaPagar() {

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

	public Integer getId() {
		return id;
	}

	public Condominio getCondominio() {
		return condominio;
	}

	public String getData() {
		return data;
	}

	public String getHistorico() {
		return historico;
	}

	public Double getDebito() {
		return debito;
	}

	public Double getCredito() {
		return credito;
	}

	public Double getSaldo() {
		return saldo;
	}

}
package br.com.titan.estacionamento.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Currency;

@Entity
public class Preco {

	@Id
	@NotEmpty
	private String tipoVeiculo;
	
	@Column(nullable = false)
	private Double precoHora;
	
	@Column(nullable = false)
	private Double precoHoraFracao;

	public Preco() {
	}

	public String getTipoVeiculo() {
		return tipoVeiculo;
	}

	public void setTipoVeiculo(String tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}

	public Double getPrecoHora() {
		return precoHora;
	}

	public void setPrecoHora(Double precoHora) {
		this.precoHora = precoHora;
	}

	public Double getPrecoHoraFracao() {
		return precoHoraFracao;
	}

	public void setPrecoHoraFracao(Double precoHoraFracao) {
		this.precoHoraFracao = precoHoraFracao;
	}

}

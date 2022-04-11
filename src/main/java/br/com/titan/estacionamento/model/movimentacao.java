package br.com.titan.estacionamento.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class movimentacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate data_entrada;

	@JsonFormat(pattern = "HH:mm:ss")
	private LocalTime hora_entrada;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate data_saida;

	@JsonFormat(pattern = "HH:mm:ss")
	private LocalTime hora_saida;

	@OneToOne
	private Parque parque;
	
	@OneToOne
	private Veiculo veiculo;

	public movimentacao() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getData_entrada() {
		return data_entrada;
	}

	public void setData_entrada(LocalDate data_entrada) {
		this.data_entrada = data_entrada;
	}

	public LocalTime getHora_entrada() {
		return hora_entrada;
	}

	public void setHora_entrada(LocalTime hora_entrada) {
		this.hora_entrada = hora_entrada;
	}

	public LocalDate getData_saida() {
		return data_saida;
	}

	public void setData_saida(LocalDate data_saida) {
		this.data_saida = data_saida;
	}

	public LocalTime getHora_saida() {
		return hora_saida;
	}

	public void setHora_saida(LocalTime hora_saida) {
		this.hora_saida = hora_saida;
	}

	public Parque getParque() {
		return parque;
	}

	public void setParque(Parque parque) {
		this.parque = parque;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data_entrada == null) ? 0 : data_entrada.hashCode());
		result = prime * result + ((data_saida == null) ? 0 : data_saida.hashCode());
		result = prime * result + ((hora_entrada == null) ? 0 : hora_entrada.hashCode());
		result = prime * result + ((hora_saida == null) ? 0 : hora_saida.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((parque == null) ? 0 : parque.hashCode());
		result = prime * result + ((veiculo == null) ? 0 : veiculo.hashCode());
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
		movimentacao other = (movimentacao) obj;
		if (data_entrada == null) {
			if (other.data_entrada != null)
				return false;
		} else if (!data_entrada.equals(other.data_entrada))
			return false;
		if (data_saida == null) {
			if (other.data_saida != null)
				return false;
		} else if (!data_saida.equals(other.data_saida))
			return false;
		if (hora_entrada == null) {
			if (other.hora_entrada != null)
				return false;
		} else if (!hora_entrada.equals(other.hora_entrada))
			return false;
		if (hora_saida == null) {
			if (other.hora_saida != null)
				return false;
		} else if (!hora_saida.equals(other.hora_saida))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (parque == null) {
			if (other.parque != null)
				return false;
		} else if (!parque.equals(other.parque))
			return false;
		if (veiculo == null) {
			if (other.veiculo != null)
				return false;
		} else if (!veiculo.equals(other.veiculo))
			return false;
		return true;
	}
	
	

}

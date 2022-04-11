package br.com.titan.estacionamento.service;

import br.com.titan.estacionamento.excecoes.CapacidadeTotalException;
import br.com.titan.estacionamento.excecoes.EstacionamentoLotadoException;
import br.com.titan.estacionamento.model.Parque;
import br.com.titan.estacionamento.repository.ParqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParqueService {
	
	@Autowired
    ParqueRepository repository;
	
	public Parque salvar(Parque parque) {
		return repository.save(parque);
	}
	
	public Parque registrarCapacidadeMaxima(Parque parque) {
		Parque parqueDoBanco = this.buscarParque();
		if(parque.getCapacidadeMaxima() == null) parque.setCapacidadeMaxima(parqueDoBanco.getCapacidadeMaxima());
		if(parque.getVagasDisponiveis() == null) parque.setVagasDisponiveis(parqueDoBanco.getVagasDisponiveis());
		return repository.save(parque);
	}
	
	public Parque buscarParque() {
		return repository.findAll().get(0);
	}
	
	/**
	 * @author Cristian Bittencourt Candia
	 * @return Diminui em uma vaga a capacidade do estacionamento ou 
	 * lança uma exceção caso o estacionamento esteja vazio.
	 */
	public Parque aumentarQuantidadeDeVagasDisponiveisEmUm() {
		
		Parque parqueVindoDoBanco = repository.findAll().get(0);
		
		Integer capacidadeMaxima = parqueVindoDoBanco.getCapacidadeMaxima();
		Integer capacidadeMinima =  capacidadeMaxima - capacidadeMaxima;
		
		/**
		 * Testa se a capacidade de vagas é mínima
		 */
		if(parqueVindoDoBanco.getId() == null) {
			
			throw new NullPointerException("Erro, id não encontrado.");
			
		}else if(parqueVindoDoBanco.getVagasDisponiveis() >= capacidadeMaxima) {
			
			parqueVindoDoBanco.setVagasDisponiveis(capacidadeMaxima);
			
			throw new CapacidadeTotalException("ESTACIONAMENTO VAZIO! Não é possivel desocupar vagas.");
		}
		
		parqueVindoDoBanco.setVagasDisponiveis(parqueVindoDoBanco.getVagasDisponiveis() + 1);
		
		return parqueVindoDoBanco;
	}
	
	/**
	 * @author Cristian Bittencourt Candia
	 * 
	 * quando o estacionamento está lotado.
	 * 
	 * @return Diminui em um a quantidade de vagas disponiveis do estacionamento quando um carro 
	 * ocupa uma vaga.
	 * 
	 */
	public Parque diminuirQuantidadeDeVagasDisponiveisEmUm() {
		
		Parque parqueVindoDoBanco = repository.findAll().get(0);
		Integer capacidadeMaxima = parqueVindoDoBanco.getCapacidadeMaxima();
		Integer capacidadeMinima =  capacidadeMaxima - capacidadeMaxima;
		
		if(parqueVindoDoBanco.getId() == null) {
			throw new NullPointerException("Erro, id não encontrado.");
		}else if(parqueVindoDoBanco.getVagasDisponiveis() <= capacidadeMinima) {
			parqueVindoDoBanco.setVagasDisponiveis(capacidadeMinima);
			throw new EstacionamentoLotadoException("ESTACIONAMENTO LOTADO! Aguarde a saída de um veículo.");
		}
		parqueVindoDoBanco.setVagasDisponiveis(parqueVindoDoBanco.getVagasDisponiveis() - 1);
		return parqueVindoDoBanco;
	}
	
}

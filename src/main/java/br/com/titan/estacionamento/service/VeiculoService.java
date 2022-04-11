package br.com.titan.estacionamento.service;

import java.util.List;

import br.com.titan.estacionamento.model.Veiculo;
import br.com.titan.estacionamento.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VeiculoService {
	
	@Autowired
    VeiculoRepository repository;
	
	/**
	 * 
	 * @param veiculo
	 * @return Retorna um JSON do veículo salvo
	 */
	public Veiculo salvar(Veiculo veiculo) {
		return repository.save(veiculo);
	}
	
	/**
	 * 
	 * @return Retorna um JSON com todos os veículos
	 */
	public List<Veiculo> buscarTodos() {
		return (List<Veiculo>) repository.findAll();
	}
	
	/**
	 * 
	 * @param veiculo
	 * @return Retorna um JSON com um veículo.
	 */
	public Veiculo buscarPorId(Veiculo veiculo) {
		return repository.findById(veiculo.getId()).get();
	}

}

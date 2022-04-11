package br.com.titan.estacionamento.service;

import br.com.titan.estacionamento.model.Preco;
import br.com.titan.estacionamento.model.Veiculo;
import br.com.titan.estacionamento.repository.PrecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrecoService {
	
	@Autowired
    PrecoRepository precoRepository;
	
	/**
	 * 
	 * @param preco
	 * @return Retorna um JSON com o registro de preço salvo, ou um erro
	 * caso não haja sucesso.
	 */
	public Preco salvar(Preco preco) {
		return precoRepository.save(preco);
	}
	
	/**
	 * 
	 * @param veiculo
	 * @return Retorna um JSON com o registro de preço baseado no tipo de veículo, ou um erro
	 * caso não haja sucesso.
	 */
	public Preco buscar(Veiculo veiculo) {
		return precoRepository.findById(veiculo.getTipoVeiculo()).get();
	}
}

package br.com.titan.estacionamento.service;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import br.com.titan.estacionamento.model.Cliente;
import br.com.titan.estacionamento.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	/**
	 * 
	 * @return Retorna um JSON com o Cliente salvo ou um erro se
	 * não for bem sucedido.
	 */
	public Cliente salvar(Cliente cliente) {
		return repository.save(cliente);
	}

	/**
	 * 
	 * @return Retorna um JSON com o um Cliente ou um erro se
	 * não for bem sucedido.
	 */	
	public Cliente buscarPorId(Cliente cliente) {
		return repository.findById(cliente.getId()).get();
	}
	
	/**
	 * 
	 * @return Retorna um JSON com o todos Clientes registrados ou um erro se
	 * não for bem sucedido.
	 */
	public ArrayList<Cliente> buscarTodos() {
		return (ArrayList<Cliente>) repository.findAll();
	}
	
	public Cliente deletar(Cliente cliente) {
		repository.delete(cliente);
		return cliente;
	}
	
	/**
	 * 
	 * @param cliente
	 * Lança uma Exception caso aconteça uma busca por cliente não existente.
	 */
	public void verificaSeClienteExiste(Cliente cliente) {
		if (repository.findById(cliente.getId()).isEmpty()) {
			throw new NoSuchElementException("Cliente não encontrado para o id: " + cliente.getId());
		}
	}

}

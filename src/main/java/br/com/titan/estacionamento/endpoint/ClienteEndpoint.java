package br.com.titan.estacionamento.endpoint;

import java.util.ArrayList;

import br.com.titan.estacionamento.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.titan.estacionamento.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteEndpoint {

	@Autowired
	private ClienteService clienteService;

	@PostMapping("salvar")
	public Cliente cadastrar(@RequestBody Cliente cliente) {
		return clienteService.salvar(cliente);
	}

	@GetMapping("buscar")
	public Cliente buscarPorId(Cliente cliente) {
		return clienteService.buscarPorId(cliente);
	}

	@DeleteMapping("deletar")
	public Cliente deletar(Cliente cliente) {
		return clienteService.deletar(cliente);
	}

	@GetMapping
	public ArrayList<Cliente> buscarTodos() {
		return (ArrayList<Cliente>) clienteService.buscarTodos();
	}
}

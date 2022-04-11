package br.com.titan.estacionamento.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import br.com.titan.estacionamento.model.Cliente;
import br.com.titan.estacionamento.repository.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ClienteServiceTest {

	private Cliente cliente = new Cliente();

	@InjectMocks
	ClienteService clienteService;

	@Mock
    ClienteRepository clienteRepository;

	@BeforeEach
	public void configuracao() {

		cliente.setId(2L);
		cliente.setNome("Jakson");
		
	}

	@Test
	public void deveBuscarClientePorId() {
		
		Mockito.when(clienteRepository.findById(anyLong())).thenReturn(java.util.Optional.of(cliente));
		
		Cliente cliente2 = clienteService.buscarPorId(cliente);

		Assertions.assertEquals(cliente.getId(), cliente.getId());
	}
	
	
	@Test
	public void deveRetornarSucessoAoSalvarCliente() {	
		
		Mockito.when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
		
		Cliente cliente2 = clienteService.salvar(cliente);
		
		Assertions.assertEquals(cliente.getId(), cliente2.getId());
	}
	
	@Test
	public void deveRetornarUmaListaDeClientes() {
		
		List<Cliente> clientes = new ArrayList<>();
		clientes.add(cliente);
		
		Mockito.when(clienteRepository.findAll()).thenReturn(clientes);
		
		List<Cliente> clientesBuscar = clienteService.buscarTodos();
		
		Assertions.assertEquals(clientes, clientesBuscar);
	}
	
	@Test
	public void deveRetornarUmaExcecao_QuandoClienteNaoExistir() {
	
		Cliente clienteVazio = new Cliente();
		
		Mockito.when(clienteRepository.findById(anyLong())).thenReturn(java.util.Optional.of(clienteVazio));
		
		Assertions.assertThrows(NoSuchElementException.class , () -> clienteService.verificaSeClienteExiste(clienteVazio));
	}

}


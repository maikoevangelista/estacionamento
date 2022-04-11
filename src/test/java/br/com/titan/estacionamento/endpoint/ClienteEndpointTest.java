package br.com.titan.estacionamento.endpoint;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import br.com.titan.estacionamento.model.Cliente;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import br.com.titan.estacionamento.repository.ClienteRepository;
import br.com.titan.estacionamento.service.ClienteService;

@ExtendWith(MockitoExtension.class) 
@EnableAutoConfiguration 
@WebMvcTest(ClienteEndpoint.class)
public class ClienteEndpointTest {
	
	private Cliente cliente = new Cliente();
	
	private ArrayList<Cliente> clientes = new ArrayList<>();
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	private ClienteRepository clienteRepository;
	
	@MockBean
	private ClienteService clienteService;
	
	@BeforeEach
	public void configuracao() {
		
		cliente.setId(1L);
		cliente.setNome("Cristian");
		cliente.setEmail("cristian.candia@gmail.com");
		cliente.setCpf("02512678067");
		cliente.setDataNascimento(LocalDate.parse("2010-10-10"));
		
		clientes.add(cliente);

	}
	
	
	@Test
	public void  deveBuscarTodosClientes() throws Exception {
		
		Mockito.when(clienteRepository.findAll()).thenReturn(clientes);
		Mockito.when(clienteService.buscarTodos()).thenReturn(clientes);
		String url = "/cliente";
		MvcResult mvcResult = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
	 
		Assertions.assertThat(mvcResult.getRequest());
	}
	
	@Test
	public void  deveBuscarClientePorId() throws Exception {
		
		Mockito.when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(cliente));
		Mockito.when(clienteService.buscarPorId(any(Cliente.class))).thenReturn(cliente);
		String url = "/cliente/buscar";
		MvcResult mvcResult = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
		
		Assertions.assertThat(mvcResult.getRequest());
	}
	
	
	
//	@Test
//	public void deveRetornarSucesso_QuandoSalvarCliente() throws Exception{
//		
//			Mockito.when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
//			Mockito.when(clienteService.salvar(any(Cliente.class))).thenReturn(cliente);
//			String url = "/cliente/salvar";
//			MvcResult mvcResult =  (MvcResult) mockMvc.perform(post(url))	
//													 .andExpect(status().isOk());
//			
//			Assertions.assertThat(mvcResult.getRequest());
//	}

}

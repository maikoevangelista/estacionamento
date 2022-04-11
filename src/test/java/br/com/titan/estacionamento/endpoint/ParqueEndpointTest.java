package br.com.titan.estacionamento.endpoint;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import br.com.titan.estacionamento.model.Parque;
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

import br.com.titan.estacionamento.repository.ParqueRepository;
import br.com.titan.estacionamento.service.ParqueService;

@ExtendWith(MockitoExtension.class) 
@EnableAutoConfiguration 
@WebMvcTest(ParqueEndpoint.class)
public class ParqueEndpointTest {
	
	List<Parque> parques = new ArrayList<>();
	Parque parque = new Parque();
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	private ParqueRepository parqueRepository;
	
	@MockBean
	private ParqueService parqueService;
	
	@BeforeEach
	public void configuracao() {
		
		parque.setId(1L);
		parque.setCapacidadeMaxima(10);
		parque.setVagasDisponiveis(9);
		
		parques.add(parque);

	}
	
	@Test
	public void  deveBuscarTodosClientes() throws Exception {
		
		Mockito.when(parqueRepository.findAll()).thenReturn(parques);
		Mockito.when(parqueService.buscarParque()).thenReturn(parque);
		String url = "/parque/buscar";
		MvcResult mvcResult = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
	 
		Assertions.assertThat(mvcResult.getRequest());
	}
}

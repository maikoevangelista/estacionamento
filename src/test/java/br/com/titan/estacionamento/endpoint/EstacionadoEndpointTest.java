package br.com.titan.estacionamento.endpoint;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import br.com.titan.estacionamento.model.Estacionado;
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

import br.com.titan.estacionamento.repository.EstacionadoRepository;
import br.com.titan.estacionamento.service.EstacionadoService;

@ExtendWith(MockitoExtension.class) 
@EnableAutoConfiguration 
@WebMvcTest(EstacionadoEndpoint.class)
public class EstacionadoEndpointTest {
	
	private List<Estacionado> estacionados = new  ArrayList<>();
	private Estacionado estacionado = new Estacionado();
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	private EstacionadoRepository estacionadoRepository;
	
	@MockBean
	private EstacionadoService estacionadoService;
	
	@BeforeEach
	public void configuracao() {
		estacionado.setId(1L);
		estacionado.setHora_saida(LocalTime.now());
		estacionado.setData_saida(LocalDate.now());
		
		estacionados.add(estacionado);
	}

	@Test
	public void  deveBuscarTodosEstacionados() throws Exception {
		
		Mockito.when(estacionadoRepository.findAll()).thenReturn(estacionados);
		Mockito.when(estacionadoService.buscarTodosRegistros()).thenReturn(estacionados);
		String url = "/estacionado";
		MvcResult mvcResult = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
	 
		Assertions.assertThat(mvcResult.getRequest());
	}
	

}

package br.com.titan.estacionamento.endpoint;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import br.com.titan.estacionamento.model.movimentacao;
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

import br.com.titan.estacionamento.repository.movimentacaoRepository;
import br.com.titan.estacionamento.service.EstacionadoService;

@ExtendWith(MockitoExtension.class) 
@EnableAutoConfiguration 
@WebMvcTest(EstacionadoEndpoint.class)
public class movimentacaoEndpointTest {
	
	private List<movimentacao> movimentacaos = new  ArrayList<>();
	private movimentacao movimentacao = new movimentacao();
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	private movimentacaoRepository movimentacaoRepository;
	
	@MockBean
	private EstacionadoService estacionadoService;
	
	@BeforeEach
	public void configuracao() {
		movimentacao.setId(1L);
		movimentacao.setHora_saida(LocalTime.now());
		movimentacao.setData_saida(LocalDate.now());
		
		movimentacaos.add(movimentacao);
	}

	@Test
	public void  deveBuscarTodosEstacionados() throws Exception {
		
		Mockito.when(movimentacaoRepository.findAll()).thenReturn(movimentacaos);
		Mockito.when(estacionadoService.buscarTodosRegistros()).thenReturn(movimentacaos);
		String url = "/estacionado";
		MvcResult mvcResult = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
	 
		Assertions.assertThat(mvcResult.getRequest());
	}
	

}

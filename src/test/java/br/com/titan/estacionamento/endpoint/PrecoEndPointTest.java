package br.com.titan.estacionamento.endpoint;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import br.com.titan.estacionamento.model.Preco;
import br.com.titan.estacionamento.model.Veiculo;
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

import br.com.titan.estacionamento.repository.PrecoRepository;
import br.com.titan.estacionamento.service.PrecoService;

@ExtendWith(MockitoExtension.class) 
@EnableAutoConfiguration 
@WebMvcTest(PrecoEndpoint.class)
public class PrecoEndPointTest {
	
	Preco preco = new Preco();
	private Veiculo veiculo = new Veiculo();
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	private PrecoRepository precoRepository;
	
	@MockBean
	private PrecoService precoService;
	
	@BeforeEach
	public void configuracao() {

		preco.setTipoVeiculo("carro");
		preco.setPrecoHora(10.0);
		preco.setPrecoHoraFracao(2.5);
		
		veiculo.setTipoVeiculo("carro");
	}
	
	@Test
	public void  deveBuscarTodosClientes() throws Exception {
		
		Mockito.when(precoRepository.findById(anyString())).thenReturn(Optional.of(preco));
		Mockito.when(precoService.buscar(any(Veiculo.class))).thenReturn(preco);
		String url = "/preco/buscar";
		MvcResult mvcResult = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
	 
		Assertions.assertThat(mvcResult.getRequest());
	}
}

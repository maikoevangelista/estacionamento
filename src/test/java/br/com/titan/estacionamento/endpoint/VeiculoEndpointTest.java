package br.com.titan.estacionamento.endpoint;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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

import br.com.titan.estacionamento.repository.VeiculoRepository;
import br.com.titan.estacionamento.service.VeiculoService;

@ExtendWith(MockitoExtension.class) 
@EnableAutoConfiguration 
@WebMvcTest(VeiculoEndpoint.class)
public class VeiculoEndpointTest {
	
	private Veiculo veiculo = new Veiculo();
	List<Veiculo> veiculos = new ArrayList<>();
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	private VeiculoRepository veiculoRepository;
	
	@MockBean
	private VeiculoService veiculoService;
	
	@BeforeEach
	public void configuracao() {
		
		veiculo.setId(1L);
		veiculo.setModelo("Celta");
		veiculo.setPlaca("QQQ000");
		veiculo.setTipoVeiculo("carro");
	}
	
	@Test
	public void  deveBuscarTodosClientes() throws Exception {
		
		Mockito.when(veiculoRepository.findAll()).thenReturn(veiculos);
		Mockito.when(veiculoService.buscarTodos()).thenReturn(veiculos);
		String url = "/veiculo/buscar";
		MvcResult mvcResult = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
	 
		Assertions.assertThat(mvcResult.getRequest());
	}

}

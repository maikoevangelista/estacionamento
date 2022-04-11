package br.com.titan.estacionamento.service;

import static org.mockito.ArgumentMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.titan.estacionamento.model.Veiculo;
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

import br.com.titan.estacionamento.repository.VeiculoRepository;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class VeiculoServiceTest {
	
	private Veiculo veiculo = new Veiculo();

	@InjectMocks
	VeiculoService veiculoService;

	@Mock
	VeiculoRepository veiculoRepository;

	@BeforeEach
	public void configuracao() {
		veiculo.setId(1L);
		veiculo.setModelo("Celta");
		veiculo.setPlaca("QQQ000");
		veiculo.setTipoVeiculo("carro");
	}
	
	@Test
	public void deveSalvarUmVeiculo() {
		
		Mockito.when(veiculoRepository.save(any(Veiculo.class))).thenReturn(veiculo);
		
		Veiculo veiculoSalvo = veiculoService.salvar(veiculo);
		
		Assertions.assertEquals(1L, veiculoSalvo.getId());
		
	}
	
	@Test
	public void deveBuscarUmVeiculoPorId() {
		
		Mockito.when(veiculoRepository.findById(anyLong())).thenReturn(Optional.of(veiculo));
		
		Veiculo veiculoRetornado = veiculoService.buscarPorId(veiculo);
		
		Assertions.assertEquals(1L, veiculoRetornado.getId());
		
	}
	
	@Test
	public void deveBuscarUmaListaDeVeiculos() {
		
		List<Veiculo> veiculos = new ArrayList<>();
		veiculos.add(veiculo);
		
		Mockito.when(veiculoRepository.findAll()).thenReturn(veiculos);
		
		List<Veiculo> veiculoRetornados = veiculoService.buscarTodos();
		
		Assertions.assertEquals(1L, veiculoRetornados.get(0).getId());
	}
	
	@Test
	public void deveRetornarListaComTamanhoCorreto() {
		
		List<Veiculo> veiculos = new ArrayList<>();
		veiculos.add(veiculo);
		
		Mockito.when(veiculoRepository.findAll()).thenReturn(veiculos);
		
		List<Veiculo> veiculoRetornados = veiculoService.buscarTodos();
		
		Assertions.assertEquals(veiculos.size(), veiculoRetornados.size());
	}
	
	
	

}


package br.com.titan.estacionamento.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import br.com.titan.estacionamento.model.movimentacao;
import br.com.titan.estacionamento.model.Parque;
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

import br.com.titan.estacionamento.repository.movimentacaoRepository;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class movimentacaoServiceTest {

	private movimentacao movimentacao = new movimentacao();
	private List<movimentacao> movimentacaos = new ArrayList<>();

	@InjectMocks
	EstacionadoService estacionadoService;

	@Mock
	movimentacaoRepository movimentacaoRepository;
	
	@Mock
	ParqueService parqueService;

	@BeforeEach
	public void configuracao() {

		movimentacao.setId(1L);
		movimentacao.setHora_saida(LocalTime.now());
		movimentacao.setData_saida(LocalDate.now());
		
		movimentacaos.add(movimentacao);

	}
	
	@Test
	public void deveRetornarUmUnicoRegistro_QuantoBuscarRegistroPorId() {
		
		Mockito.when(movimentacaoRepository.findById(anyLong())).thenReturn(java.util.Optional.of(movimentacao));
		
		movimentacao movimentacao2 = estacionadoService.buscarRegistroPorId(movimentacao);

		Assertions.assertEquals(movimentacao.getId(), movimentacao.getId());
	}
	
	@Test
	public void deveRetornarTodosOsRegistros() {
		
		Mockito.when(movimentacaoRepository.findAll()).thenReturn(movimentacaos);
		
		List<movimentacao> estacionados2 = estacionadoService.buscarTodosRegistros();
		
		Assertions.assertEquals(movimentacaos.get(0).getId(), estacionados2.get(0).getId());
	}

	@Test
	public void deveRegistrarDataDoMomentoQueForChamado_DataEntrada() {

		Mockito.when(movimentacaoRepository.save(any(movimentacao.class))).thenReturn(movimentacao);

		movimentacao registroDataEntrada = estacionadoService.registrarEntrada(movimentacao);

		LocalDate dataHoje = LocalDate.now();

		Assertions.assertEquals(dataHoje, registroDataEntrada.getData_entrada());
	}

	@Test
	public void deveRegistrarHoraDoMomentoQueForChamado_HoraEntrada() {

		Mockito.when(movimentacaoRepository.save(any(movimentacao.class))).thenReturn(movimentacao);

		movimentacao registroHoraEntrada = estacionadoService.registrarEntrada(movimentacao);

		LocalTime horarioCorreto = LocalTime.now();

		Assertions.assertEquals(horarioCorreto, registroHoraEntrada.getHora_entrada());

	}

	@Test
	public void deveRegistrarDataDoMomentoQueForChamado_DataSaida() {

		movimentacao registroDataSaida = estacionadoService.registrarDataSaida(movimentacao);

		LocalDate dataHoje = LocalDate.now();

		Assertions.assertEquals(dataHoje, registroDataSaida.getData_saida());
	}

	@Test
	public void deveRegistrarHoraDoMomentoQueForChamado_HoraSaida() {
		
		movimentacao registroHoraSaida = estacionadoService.registrarHoraSaida(movimentacao);
		
		LocalTime horarioCorreto = LocalTime.now();

		Assertions.assertEquals(horarioCorreto, registroHoraSaida.getHora_saida());

	}
	
	@Test 
	public void deveSalvarDados_QuandoPreencherDadosManualmente() {
		Parque parque = new Parque();
		parque.setVagasDisponiveis(10);
		parque.setId(1L);
		
		Mockito.when(parqueService.buscarParque()).thenReturn(parque);
		
		movimentacao movimentacaoManual = new movimentacao();
		movimentacaoManual.setData_entrada(LocalDate.parse("2001-01-01"));
		movimentacaoManual.setHora_entrada(LocalTime.parse("08:24"));
		movimentacaoManual.setParque(parque);
		movimentacaoManual.setVeiculo(new Veiculo());
		
		movimentacao movimentacaoManualTeste = new movimentacao();
		movimentacaoManualTeste.setData_entrada(LocalDate.parse("2001-01-01"));
		movimentacaoManualTeste.setHora_entrada(LocalTime.parse("08:24"));
		movimentacaoManualTeste.setParque(parque);
		movimentacaoManualTeste.setVeiculo(new Veiculo());
		
		movimentacao movimentacaoTeste = estacionadoService.preencherDadosManualmente(movimentacaoManualTeste);
		
		Assertions.assertEquals(movimentacaoManualTeste, movimentacaoTeste);
		
	}

}

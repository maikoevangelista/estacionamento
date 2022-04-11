
package br.com.titan.estacionamento.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import br.com.titan.estacionamento.model.Estacionado;
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

import br.com.titan.estacionamento.repository.EstacionadoRepository;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class EstacionadoServiceTest {

	private Estacionado estacionado = new Estacionado();
	private List<Estacionado> estacionados = new ArrayList<>();

	@InjectMocks
	EstacionadoService estacionadoService;

	@Mock
	EstacionadoRepository estacionadoRepository;
	
	@Mock
	ParqueService parqueService;

	@BeforeEach
	public void configuracao() {

		estacionado.setId(1L);
		estacionado.setHora_saida(LocalTime.now());
		estacionado.setData_saida(LocalDate.now());
		
		estacionados.add(estacionado);

	}
	
	@Test
	public void deveRetornarUmUnicoRegistro_QuantoBuscarRegistroPorId() {
		
		Mockito.when(estacionadoRepository.findById(anyLong())).thenReturn(java.util.Optional.of(estacionado));
		
		Estacionado estacionado2 = estacionadoService.buscarRegistroPorId(estacionado);

		Assertions.assertEquals(estacionado.getId(), estacionado.getId());
	}
	
	@Test
	public void deveRetornarTodosOsRegistros() {
		
		Mockito.when(estacionadoRepository.findAll()).thenReturn(estacionados);
		
		List<Estacionado> estacionados2 = estacionadoService.buscarTodosRegistros();
		
		Assertions.assertEquals(estacionados.get(0).getId(), estacionados2.get(0).getId());
	}

	@Test
	public void deveRegistrarDataDoMomentoQueForChamado_DataEntrada() {

		Mockito.when(estacionadoRepository.save(any(Estacionado.class))).thenReturn(estacionado);

		Estacionado registroDataEntrada = estacionadoService.registrarEntrada(estacionado);

		LocalDate dataHoje = LocalDate.now();

		Assertions.assertEquals(dataHoje, registroDataEntrada.getData_entrada());
	}

	@Test
	public void deveRegistrarHoraDoMomentoQueForChamado_HoraEntrada() {

		Mockito.when(estacionadoRepository.save(any(Estacionado.class))).thenReturn(estacionado);

		Estacionado registroHoraEntrada = estacionadoService.registrarEntrada(estacionado);

		LocalTime horarioCorreto = LocalTime.now();

		Assertions.assertEquals(horarioCorreto, registroHoraEntrada.getHora_entrada());

	}

	@Test
	public void deveRegistrarDataDoMomentoQueForChamado_DataSaida() {

		Estacionado registroDataSaida = estacionadoService.registrarDataSaida(estacionado);

		LocalDate dataHoje = LocalDate.now();

		Assertions.assertEquals(dataHoje, registroDataSaida.getData_saida());
	}

	@Test
	public void deveRegistrarHoraDoMomentoQueForChamado_HoraSaida() {
		
		Estacionado registroHoraSaida = estacionadoService.registrarHoraSaida(estacionado);
		
		LocalTime horarioCorreto = LocalTime.now();

		Assertions.assertEquals(horarioCorreto, registroHoraSaida.getHora_saida());

	}
	
	@Test 
	public void deveSalvarDados_QuandoPreencherDadosManualmente() {
		Parque parque = new Parque();
		parque.setVagasDisponiveis(10);
		parque.setId(1L);
		
		Mockito.when(parqueService.buscarParque()).thenReturn(parque);
		
		Estacionado estacionadoManual = new Estacionado();
		estacionadoManual.setData_entrada(LocalDate.parse("2001-01-01"));
		estacionadoManual.setHora_entrada(LocalTime.parse("08:24"));
		estacionadoManual.setParque(parque);
		estacionadoManual.setVeiculo(new Veiculo());
		
		Estacionado estacionadoManualTeste = new Estacionado();
		estacionadoManualTeste.setData_entrada(LocalDate.parse("2001-01-01"));
		estacionadoManualTeste.setHora_entrada(LocalTime.parse("08:24"));
		estacionadoManualTeste.setParque(parque);
		estacionadoManualTeste.setVeiculo(new Veiculo());
		
		Estacionado estacionadoTeste = estacionadoService.preencherDadosManualmente(estacionadoManualTeste);
		
		Assertions.assertEquals(estacionadoManualTeste, estacionadoTeste);
		
	}

}

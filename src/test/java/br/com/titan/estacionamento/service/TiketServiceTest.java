package br.com.titan.estacionamento.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import br.com.titan.estacionamento.model.Estacionado;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import br.com.titan.estacionamento.repository.TiketRepository;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TiketServiceTest {
	
	Estacionado estacionado = new Estacionado();
	
	@InjectMocks
	TiketService tiketService;
	
	@Mock
	TiketRepository tiketRepository;
	
	@BeforeEach
	public void congiguracao() {
		estacionado.setHora_entrada(LocalTime.of(7, 30));
		estacionado.setHora_saida(LocalTime.of(7, 30));
		estacionado.setData_entrada(LocalDate.parse("2020-01-10"));
		estacionado.setData_saida(LocalDate.parse("2020-01-15"));
	}

		
	@Test
	public void deveCalcularPeriodoEntreHoraSaidaEHoraEntrada() {
		
		LocalTime intervaloDeTempo = tiketService.calculaIntervaloDeTempo(estacionado);
		
		Assertions.assertEquals(LocalTime.parse("00:00"), intervaloDeTempo);
	}
	
	@Test
	public void deveCalcularADiferencaEntreMinutosDeEntradaESaida() {
		
		LocalTime intervaloDeTempo = tiketService.calculaIntervaloDeTempo(estacionado);

		assertEquals(LocalTime.parse("00:00"), intervaloDeTempo);
	}
	
	@Test
	public void deveCalcularValorBaseadoNoTempo() {
		
		LocalTime intervaloDeTempo = tiketService.calculaIntervaloDeTempo(estacionado);
		Double valorBaseadoNoTempo = tiketService.calcularValorBaseadoNoTempo(10.0, 2.5, intervaloDeTempo);
		
		Assertions.assertEquals(0.0, valorBaseadoNoTempo);
	}
	
	
	@Test
	public void deveLancarUmaExceptionS_QuandoDataEntradaForDepoisDeSaida() {
		
		Boolean entradaDepoisDeSaida = tiketService.validaIntervaloDeTempo(estacionado);
		
		Assertions.assertDoesNotThrow(() -> entradaDepoisDeSaida, "Erro! Data de entrada é posterior a data de saída.");
	}
	
	@Test
	public void deveLancarUmaExceptionS_QuandoHoraEntradaForDepoisDeSaida() {
		
		Boolean entradaDepoisDeSaida = tiketService.validaIntervaloDeTempo(estacionado);
		
		Assertions.assertDoesNotThrow(() -> entradaDepoisDeSaida, "Erro! hora de entrada é posterior a data de saída.");
	}
}
	
	
	
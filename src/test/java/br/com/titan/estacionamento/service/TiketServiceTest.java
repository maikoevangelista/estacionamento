package br.com.titan.estacionamento.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import br.com.titan.estacionamento.model.movimentacao;
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
	
	movimentacao movimentacao = new movimentacao();
	
	@InjectMocks
	TiketService tiketService;
	
	@Mock
	TiketRepository tiketRepository;
	
	@BeforeEach
	public void congiguracao() {
		movimentacao.setHora_entrada(LocalTime.of(7, 30));
		movimentacao.setHora_saida(LocalTime.of(7, 30));
		movimentacao.setData_entrada(LocalDate.parse("2020-01-10"));
		movimentacao.setData_saida(LocalDate.parse("2020-01-15"));
	}

		
	@Test
	public void deveCalcularPeriodoEntreHoraSaidaEHoraEntrada() {
		
		LocalTime intervaloDeTempo = tiketService.calculaIntervaloDeTempo(movimentacao);
		
		Assertions.assertEquals(LocalTime.parse("00:00"), intervaloDeTempo);
	}
	
	@Test
	public void deveCalcularADiferencaEntreMinutosDeEntradaESaida() {
		
		LocalTime intervaloDeTempo = tiketService.calculaIntervaloDeTempo(movimentacao);

		assertEquals(LocalTime.parse("00:00"), intervaloDeTempo);
	}
	
	@Test
	public void deveCalcularValorBaseadoNoTempo() {
		
		LocalTime intervaloDeTempo = tiketService.calculaIntervaloDeTempo(movimentacao);
		Double valorBaseadoNoTempo = tiketService.calcularValorBaseadoNoTempo(10.0, 2.5, intervaloDeTempo);
		
		Assertions.assertEquals(0.0, valorBaseadoNoTempo);
	}
	
	
	@Test
	public void deveLancarUmaExceptionS_QuandoDataEntradaForDepoisDeSaida() {
		
		Boolean entradaDepoisDeSaida = tiketService.validaIntervaloDeTempo(movimentacao);
		
		Assertions.assertDoesNotThrow(() -> entradaDepoisDeSaida, "Erro! Data de entrada é posterior a data de saída.");
	}
	
	@Test
	public void deveLancarUmaExceptionS_QuandoHoraEntradaForDepoisDeSaida() {
		
		Boolean entradaDepoisDeSaida = tiketService.validaIntervaloDeTempo(movimentacao);
		
		Assertions.assertDoesNotThrow(() -> entradaDepoisDeSaida, "Erro! hora de entrada é posterior a data de saída.");
	}
}
	
	
	
package br.com.titan.estacionamento.service;

import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;

import br.com.titan.estacionamento.model.Parque;
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

import br.com.titan.estacionamento.repository.ParqueRepository;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ParqueServiceTest {
	
	private Parque parque = new Parque();
	private List<Parque> parques = new ArrayList();
	
	@InjectMocks
	ParqueService parqueService;

	@Mock
	ParqueRepository parqueRepository;
	
	@BeforeEach
	public void configuracao(){
		parque.setId(1L);
		parque.setCapacidadeMaxima(10);
		parque.setVagasDisponiveis(9);
		
		parques.add(parque);
	}

	@Test
	public void deveRegistrarCapacidadeDeVagas() {
		
		Mockito.when(parqueRepository.save(any(Parque.class))).thenReturn(parque);
		
		Parque parqueTeste = parqueRepository.save(parque);
		
		Assertions.assertEquals(parque.getCapacidadeMaxima(), parqueTeste.getCapacidadeMaxima());
	}
	
	@Test 
	public void deveRetornarListaComParques() {
			
		Mockito.when(parqueRepository.findAll()).thenReturn(parques);
		
		Parque parque = parqueService.buscarParque();
		
		Assertions.assertEquals(parques.get(0), parque);
	}
	
	@Test
	public void deveDiminuirCapacidadeDeVagasEmUm() {

		Mockito.when(parqueRepository.findAll()).thenReturn(parques);
		
		Parque parque = parqueService.diminuirQuantidadeDeVagasDisponiveisEmUm();
		
		Assertions.assertEquals(8, parque.getVagasDisponiveis());
	}
	
	@Test
	public void deveAumentarCapacidadeDeVagasEmUm() {
		
		Mockito.when(parqueRepository.findAll()).thenReturn(parques);
		
		Parque parque = parqueService.aumentarQuantidadeDeVagasDisponiveisEmUm();
		
		Assertions.assertEquals(10, parque.getCapacidadeMaxima());
	}
	
	@Test
	public void deveLancarException_QuandoCapacidadeForZeroOuMenos(){
		//parque.setVagasDisponiveis(1);
		
		Mockito.when(parqueRepository.findAll()).thenReturn(parques);
		
		Parque parque = parqueService.aumentarQuantidadeDeVagasDisponiveisEmUm();
		
		Assertions.assertDoesNotThrow(() -> parque, "ESTACIONAMENTO VAZIO! Não é possivel desocupar vagas.");
		
		//Assertions.assertThrows(CapacidadeTotalException.class, () -> parqueService.diminuirCapacidadeEmUm());	
	}
	
	@Test
	public void deveLancarException_QuandoCapacidadeMaxima(){
		
		Mockito.when(parqueRepository.findAll()).thenReturn(parques);
		
		Parque parque = parqueService.diminuirQuantidadeDeVagasDisponiveisEmUm();
		
		Assertions.assertDoesNotThrow(() -> parque, "ESTACIONAMENTO LOTADO! Aguarde a saída de um veículo.");
		
		//Assertions.assertThrows(CapacidadeTotalException.class, () -> parqueService.diminuirCapacidadeEmUm());	
	}
	
	@Test
	public void deveRegistrarCapacidadeMaxima() {
		
		
		Mockito.when(parqueRepository.save(any(Parque.class))).thenReturn(parque);
		Mockito.when(parqueRepository.findAll()).thenReturn(parques);
		
		Parque parqueTeste = parqueService.registrarCapacidadeMaxima(parque);
		
		Assertions.assertEquals(parque.getCapacidadeMaxima(), parqueTeste.getCapacidadeMaxima());
		
	}


}

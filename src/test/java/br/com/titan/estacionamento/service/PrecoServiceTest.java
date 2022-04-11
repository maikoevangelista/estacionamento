package br.com.titan.estacionamento.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import br.com.titan.estacionamento.model.Preco;
import br.com.titan.estacionamento.model.Veiculo;
import br.com.titan.estacionamento.repository.PrecoRepository;
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

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PrecoServiceTest {
	
	private Preco preco = new Preco();
	private Veiculo veiculo = new Veiculo();
	
	@InjectMocks
	PrecoService precoService;

	@Mock
    PrecoRepository precoRepository;
	
	@BeforeEach
	public void configuracao() {
		
		preco.setTipoVeiculo("carro");
		preco.setPrecoHora(10.0);
		preco.setPrecoHoraFracao(2.5);
		
		veiculo.setTipoVeiculo("carro");
	}

	@Test
	public void deveSalvarPrecoPorHora() {
		
		Mockito.when(precoRepository.save(any(Preco.class))).thenReturn(preco);
		
		Preco precoSalvo = precoRepository.save(preco);
		
		Assertions.assertEquals(preco.getPrecoHora(), precoSalvo.getPrecoHora());
	}
	
	@Test
	public void deveSalvarPrecoPorHoraFracao() {
		
		Mockito.when(precoRepository.save(any(Preco.class))).thenReturn(preco);
		
		Preco precoSalvo = precoRepository.save(preco);
		
		Assertions.assertEquals(preco.getPrecoHoraFracao(), precoSalvo.getPrecoHoraFracao());
	}
	
	@Test
	public void deveSalvarCategoriaVeiculo() {
	
		Mockito.when(precoRepository.save(any(Preco.class))).thenReturn(preco);
		
		
		Preco precoSalvo = precoService.salvar(preco);

		
		Assertions.assertEquals("carro", precoSalvo.getTipoVeiculo());
	}
	
	@Test
	public void deveBuscarPrecoReferenteATipoDeVeiculo() {
		
		Mockito.when(precoRepository.findById(anyString())).thenReturn(java.util.Optional.of(preco));
		
		Preco precoTeste = precoService.buscar(veiculo);
		
		Assertions.assertEquals(veiculo.getTipoVeiculo(), precoTeste.getTipoVeiculo());
	}
	
	
}

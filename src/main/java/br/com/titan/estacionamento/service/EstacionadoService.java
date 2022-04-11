package br.com.titan.estacionamento.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import br.com.titan.estacionamento.model.movimentacao;
import br.com.titan.estacionamento.model.Tiket;
import br.com.titan.estacionamento.repository.movimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Cristian Bittencourt Candia
 * @version 0.0.1
 * 
 */
@Service
public class EstacionadoService {
	
	@Autowired
	private movimentacaoRepository repository;
	
	@Autowired
	PrecoService precoService;
	
	@Autowired
	TiketService tiketService;
	
	@Autowired
	ParqueService parqueService;
	
	/**
	 * @author Cristian Bittencourt Candia
	 * @param  Recebe um Objeto do tipo Estacionado como parâmetro
	 * @return Retorna um Objeto com registro de entrada de um veículo no estacionamento, 
	 * com id do registro, data e hora de entrata, id do parque e id do veículo.
	 */
	public movimentacao registrarEntrada(movimentacao movimentacao) {
		
		movimentacao.setData_entrada(LocalDate.now());
		movimentacao.setHora_entrada(LocalTime.now());
		movimentacao.setParque(parqueService.buscarParque());
		
		parqueService.diminuirQuantidadeDeVagasDisponiveisEmUm();
		
		return repository.save(movimentacao);
	}
	
	/**
	 * @author Cristian Bittencourt Candia
	 * @param movimentacao
	 * @return Retorna um Objeto "cupom de pagamento" com os dados do cliente, data e hora de saida e entrada
	 * dados do veículo, preço por hora e hora fração e preço total a ser pago.
	 */
	public Tiket registrarSaida(movimentacao movimentacao) {
		
		movimentacao movimentacaoSaindo = repository.findById(movimentacao.getId()).get();
		movimentacao movimentacaoSaindoRegistrado = registrarHoraSaida(registrarDataSaida(movimentacaoSaindo));
		
		repository.save(movimentacaoSaindoRegistrado);
		
		parqueService.aumentarQuantidadeDeVagasDisponiveisEmUm();
		
		return tiketService.gerarCupomPagamento(movimentacaoSaindoRegistrado);
	}
	
	/**
	 * 
	 * @author Cristian Bittencourt Candia
	 * @param movimentacao
	 * @return Retorna um Objeto de Estacionado com hora de saída registrada.
	 */
	public movimentacao registrarHoraSaida(movimentacao movimentacao) {
			movimentacao.setHora_saida(LocalTime.now());
			
			return movimentacao;
	}
	
	/**
	 * @author Cristian Bittencourt Candia
	 * @param movimentacao
	 * @return Registra a hora de saida e retorna um Objeto com todos os demais registros de 
	 * estacionamento.
	 */
	public movimentacao registrarDataSaida(movimentacao movimentacao) {
		
		movimentacao.setData_saida(LocalDate.now());
		
		return movimentacao;
	}
	
	/**
	 * @author Cristian Bittencourt Candia
	 * @param movimentacao
	 * @return Retorna um Objeto com o registro de estacionamento.
	 */
	public movimentacao buscarRegistroPorId(movimentacao movimentacao) {
		return repository.findById(movimentacao.getId()).get();
	}
	
	/**
	 * @author Cristian Bittencourt Candia
	 * @return Retorna uma lista com todos os registros de estacionamento.
	 */
	public List<movimentacao> buscarTodosRegistros(){
		return (List<movimentacao>) repository.findAll();
	}
	/**
	 * @author Cristian Bittencour Candia
	 * @param movimentacao
	 * @return Retorna os dados de registro de entrada no estacionamento preenchidos manualmente.
	 */
	public movimentacao preencherDadosManualmente(movimentacao movimentacao) {
		movimentacao movimentacaoManual = new movimentacao();
		movimentacaoManual.setData_entrada(movimentacao.getData_entrada());
		movimentacaoManual.setHora_entrada(movimentacao.getHora_entrada());
		movimentacaoManual.setParque(parqueService.buscarParque());
		movimentacaoManual.setVeiculo(movimentacao.getVeiculo());
		return movimentacaoManual;
	}
}

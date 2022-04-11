package br.com.titan.estacionamento.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import br.com.titan.estacionamento.model.Estacionado;
import br.com.titan.estacionamento.model.Tiket;
import br.com.titan.estacionamento.repository.EstacionadoRepository;
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
	private EstacionadoRepository repository;
	
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
	public Estacionado registrarEntrada(Estacionado estacionado) {
		
		estacionado.setData_entrada(LocalDate.now());
		estacionado.setHora_entrada(LocalTime.now());
		estacionado.setParque(parqueService.buscarParque());
		
		parqueService.diminuirQuantidadeDeVagasDisponiveisEmUm();
		
		return repository.save(estacionado);
	}
	
	/**
	 * @author Cristian Bittencourt Candia
	 * @param estacionado
	 * @return Retorna um Objeto "cupom de pagamento" com os dados do cliente, data e hora de saida e entrada
	 * dados do veículo, preço por hora e hora fração e preço total a ser pago.
	 */
	public Tiket registrarSaida(Estacionado estacionado) {
		
		Estacionado estacionadoSaindo = repository.findById(estacionado.getId()).get();
		Estacionado estacionadoSaindoRegistrado = registrarHoraSaida(registrarDataSaida(estacionadoSaindo));
		
		repository.save(estacionadoSaindoRegistrado);
		
		parqueService.aumentarQuantidadeDeVagasDisponiveisEmUm();
		
		return tiketService.gerarCupomPagamento(estacionadoSaindoRegistrado);
	}
	
	/**
	 * 
	 * @author Cristian Bittencourt Candia
	 * @param estacionado
	 * @return Retorna um Objeto de Estacionado com hora de saída registrada.
	 */
	public Estacionado registrarHoraSaida(Estacionado estacionado) {	
			estacionado.setHora_saida(LocalTime.now());
			
			return estacionado;
	}
	
	/**
	 * @author Cristian Bittencourt Candia
	 * @param estacionado
	 * @return Registra a hora de saida e retorna um Objeto com todos os demais registros de 
	 * estacionamento.
	 */
	public Estacionado registrarDataSaida(Estacionado estacionado) {
		
		estacionado.setData_saida(LocalDate.now());
		
		return estacionado;	
	}
	
	/**
	 * @author Cristian Bittencourt Candia
	 * @param estacionado
	 * @return Retorna um Objeto com o registro de estacionamento.
	 */
	public Estacionado buscarRegistroPorId(Estacionado estacionado) {
		return repository.findById(estacionado.getId()).get();
	}
	
	/**
	 * @author Cristian Bittencourt Candia
	 * @return Retorna uma lista com todos os registros de estacionamento.
	 */
	public List<Estacionado> buscarTodosRegistros(){
		return (List<Estacionado>) repository.findAll();
	}
	/**
	 * @author Cristian Bittencour Candia
	 * @param estacionado
	 * @return Retorna os dados de registro de entrada no estacionamento preenchidos manualmente.
	 */
	public Estacionado preencherDadosManualmente(Estacionado estacionado) {
		Estacionado estacionadoManual = new Estacionado();
		estacionadoManual.setData_entrada(estacionado.getData_entrada());
		estacionadoManual.setHora_entrada(estacionado.getHora_entrada());
		estacionadoManual.setParque(parqueService.buscarParque());
		estacionadoManual.setVeiculo(estacionado.getVeiculo());
		return estacionadoManual;
	}
}

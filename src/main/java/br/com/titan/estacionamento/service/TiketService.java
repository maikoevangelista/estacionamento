package br.com.titan.estacionamento.service;

import java.time.LocalDate;
import java.time.LocalTime;

import br.com.titan.estacionamento.model.Cliente;
import br.com.titan.estacionamento.model.movimentacao;
import br.com.titan.estacionamento.model.Preco;
import br.com.titan.estacionamento.model.Veiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.titan.estacionamento.model.Tiket;
import br.com.titan.estacionamento.repository.TiketRepository;

@Service
public class TiketService {
	@Autowired
	TiketRepository repository;
	
	@Autowired
	PrecoService precoService;
	
	@Autowired
	EstacionadoService estacionadoService;
	
	@Autowired
	VeiculoService veiculoService;
	
	@Autowired
	ClienteService clienteService;
	
	/**
	 * 
	 * @param movimentacao
	 * @return Retorna um Cupom para pagamento, com os dados pessoais do cliente, data e hora de entrada 
	 * e saída, valor total a ser pago, valor por hora e valor hora fração.
	 */
	public Tiket gerarCupomPagamento(movimentacao movimentacao) {
		
		/* 
		 * Busca registros de entrada e saída do veículo que está saindo. 
		 */
		movimentacao movimentacaoComSaidaRegistrada = estacionadoService.buscarRegistroPorId(movimentacao);
		
		LocalDate dataEntrada = movimentacaoComSaidaRegistrada.getData_entrada();
		LocalDate dataSaida = movimentacaoComSaidaRegistrada.getData_saida();
		LocalTime horaEntrada = movimentacaoComSaidaRegistrada.getHora_entrada();
		LocalTime horaSaida = movimentacaoComSaidaRegistrada.getHora_saida();
		
		/*
		 * Busca registros do veículo que está saindo.
		 */
		Veiculo veiculoSaindo = veiculoService.buscarPorId(movimentacaoComSaidaRegistrada.getVeiculo());
		
		String tipoVeiculoSaindo = veiculoSaindo.getTipoVeiculo();
		String placaVeiculoSaindo = veiculoSaindo.getPlaca();
		String modeloVeiculoSaindo = veiculoSaindo.getModelo();
		
		/*
		 * Busca registros do cliente que está saindo.
		 */
		Cliente clienteSaindo = clienteService.buscarPorId(veiculoSaindo.getCliente());
		
		String nomeClienteSaindo = clienteSaindo.getNome();
		String cpfClienteSaindo = clienteSaindo.getCpf();
		
		/*
		 * Busca pelos valores de hora e hora fração referentes ao tipo de veículo do cliente.
		 */
		Preco precoPorTempo = precoService.buscar(veiculoSaindo);
		
		Double precoPorHora = precoPorTempo.getPrecoHora();
		Double precoHoraFracao = precoPorTempo.getPrecoHoraFracao();
		
		/*
		 * validaIntervaloDeTempo(estacionado);
		 */
		LocalTime intervaloDeTempo = calculaIntervaloDeTempo(movimentacao);
		
		Double valorTotal = calcularValorBaseadoNoTempo(precoPorHora, precoHoraFracao, intervaloDeTempo);
		
		/*
		 * Registrando as informações no cupom.
		 */
		Tiket cupomPagamento = new Tiket();
		cupomPagamento.setNomeCliente(nomeClienteSaindo);
		cupomPagamento.setCPFCliente(cpfClienteSaindo);
		cupomPagamento.setTipoVeiculo(tipoVeiculoSaindo);
		cupomPagamento.setVeiculoPlaca(placaVeiculoSaindo);
		cupomPagamento.setDataEntrada(dataEntrada);
		cupomPagamento.setDataSaida(dataSaida);
		cupomPagamento.setHoraEntrada(horaEntrada);
		cupomPagamento.setHoraSaida(horaSaida);
		cupomPagamento.setPrecoHora(precoPorHora);
		cupomPagamento.setValorTotal(valorTotal);
		
		return repository.save(cupomPagamento);
	}
	 
	/**
	 * 
	 * @param movimentacao
	 * @return Retorna o tempo que o veículo ficou estacionado.
	 */
	public LocalTime calculaIntervaloDeTempo(movimentacao movimentacao) {
		return movimentacao.getHora_saida()
						  .minusHours(movimentacao.getHora_entrada()
						  .getHour())
					      .minusMinutes(movimentacao.getHora_entrada().getMinute());
	}
	
	/**
	 * 
	 * @param precoPorHora
	 * @param precoHoraFracao
	 * @param intervaloDeTempo
	 * @return retorna o valor total a ser pago pelo tempo estacionado.
	 */
	public Double calcularValorBaseadoNoTempo(Double precoPorHora, Double precoHoraFracao, LocalTime intervaloDeTempo) {
		
		Integer tempoTotalHoras = intervaloDeTempo.getHour();
		Integer tempoTotalMinutos = intervaloDeTempo.getMinute();
		
		Double valorHoras = tempoTotalHoras * precoPorHora;
		Double valorDemais = (tempoTotalMinutos / 4) * precoHoraFracao;
		
		Double valorTotal = valorHoras + valorDemais;
		
		return valorTotal;
	}
	
	/**
	 * 
	 * @param movimentacao
	 * @return Retorna false se a data de saida for antes da data de entrada
	 * ou se a hora de saída for antes da hora de entrada, e retorna true se 
	 * não houver nenhum destes erros.
	 */
	public Boolean validaIntervaloDeTempo(movimentacao movimentacao) {
		
		if(movimentacao.getData_entrada().isAfter(movimentacao.getData_saida())) {
			
			throw new RuntimeException("Erro! Data de entrada é posterior a data de saída.");
			
		}else if(movimentacao.getHora_entrada().isAfter(movimentacao.getHora_saida())) {
			
			throw new RuntimeException("Erro! hora de entrada é posterior a hora de saída.");
			
		}
		
		return true;
	}
	
	
}

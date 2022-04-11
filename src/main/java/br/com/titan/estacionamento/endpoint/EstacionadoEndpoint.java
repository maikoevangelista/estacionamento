package br.com.titan.estacionamento.endpoint;

import java.util.List;

import br.com.titan.estacionamento.model.movimentacao;
import br.com.titan.estacionamento.model.Tiket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.titan.estacionamento.service.EstacionadoService;

@RestController
@RequestMapping("/estacionado")
public class EstacionadoEndpoint {

	@Autowired
	EstacionadoService estacionadoService;

	@PostMapping("salvar")
	public movimentacao registrarEntradaNoEstacionamento(movimentacao movimentacao) {
		return estacionadoService.registrarEntrada(movimentacao);
	}
	
	@PostMapping("preencher")
	public movimentacao preencherDadosManualmente(movimentacao movimentacao) {
		return estacionadoService.preencherDadosManualmente(movimentacao);
	}

	@RequestMapping
	public Tiket registrarSaidaDoEstacionamento(movimentacao movimentacao) {
		return estacionadoService.registrarSaida(movimentacao);
	}

	@GetMapping
	public List<movimentacao> buscarTodosRegisrosDeEstacionamento() {
		return estacionadoService.buscarTodosRegistros();
	}
}

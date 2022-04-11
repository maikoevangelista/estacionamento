package br.com.titan.estacionamento.endpoint;

import java.util.List;

import br.com.titan.estacionamento.model.Estacionado;
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
	public Estacionado registrarEntradaNoEstacionamento(Estacionado estacionado) {
		return estacionadoService.registrarEntrada(estacionado);
	}
	
	@PostMapping("preencher")
	public Estacionado preencherDadosManualmente(Estacionado estacionado) {
		return estacionadoService.preencherDadosManualmente(estacionado);
	}

	@RequestMapping
	public Tiket registrarSaidaDoEstacionamento(Estacionado estacionado) {
		return estacionadoService.registrarSaida(estacionado);
	}

	@GetMapping
	public List<Estacionado> buscarTodosRegisrosDeEstacionamento() {
		return estacionadoService.buscarTodosRegistros();
	}
}

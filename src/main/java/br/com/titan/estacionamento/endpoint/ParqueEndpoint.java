package br.com.titan.estacionamento.endpoint;

import br.com.titan.estacionamento.model.Parque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.titan.estacionamento.service.ParqueService;

@RestController
@RequestMapping("/parque")
public class ParqueEndpoint {

	@Autowired
	ParqueService parqueService;

	@PostMapping("salvar")
	public Parque salvar(@RequestBody Parque parque) {
		return parqueService.salvar(parque);
	}
	
	@RequestMapping
	public Parque alterarCapacidade(Parque parque) {
		return parqueService.registrarCapacidadeMaxima(parque);	
	}
	
	@GetMapping("buscar")
	public Parque buscar(Parque parque) {
		return parqueService.buscarParque();
	}

}

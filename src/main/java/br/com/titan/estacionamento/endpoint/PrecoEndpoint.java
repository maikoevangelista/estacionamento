package br.com.titan.estacionamento.endpoint;

import br.com.titan.estacionamento.model.Preco;
import br.com.titan.estacionamento.model.Veiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.titan.estacionamento.service.PrecoService;

@RestController
@RequestMapping("/preco")
public class PrecoEndpoint {
	
	@Autowired
	PrecoService precoService;
	
	@PostMapping("salvar")
	public Preco salvar(@RequestBody Preco preco) {
		return precoService.salvar(preco);
	}
	
	@GetMapping("buscar")
	public Preco buscar(Veiculo veiculo){
		return precoService.buscar(veiculo);
	}
}

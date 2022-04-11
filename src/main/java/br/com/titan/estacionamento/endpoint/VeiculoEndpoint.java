package br.com.titan.estacionamento.endpoint;

import java.util.List;

import br.com.titan.estacionamento.model.Veiculo;
import br.com.titan.estacionamento.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/veiculo")
public class VeiculoEndpoint {
	
	@Autowired
	private VeiculoService veiculoService;
	
	@PostMapping("salvar")
	public Veiculo salvar(Veiculo veiculo) {
		return veiculoService.salvar(veiculo);
	}
	
	@GetMapping("buscar")
	public List<Veiculo> buscar() {
		return veiculoService.buscarTodos();
	}
	
	@GetMapping
	public Veiculo buscarPorId(Veiculo veiculo) {
		return veiculoService.buscarPorId(veiculo);
	}
}

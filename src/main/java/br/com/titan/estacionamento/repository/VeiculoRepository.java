package br.com.titan.estacionamento.repository;

import br.com.titan.estacionamento.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long>{

}

package br.com.titan.estacionamento.repository;

import br.com.titan.estacionamento.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	
}

package br.com.titan.estacionamento.repository;

import br.com.titan.estacionamento.model.Parque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParqueRepository extends JpaRepository<Parque, Long>{

}

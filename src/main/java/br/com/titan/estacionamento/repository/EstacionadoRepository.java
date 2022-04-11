package br.com.titan.estacionamento.repository;

import br.com.titan.estacionamento.model.Estacionado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstacionadoRepository extends JpaRepository<Estacionado, Long>{

}

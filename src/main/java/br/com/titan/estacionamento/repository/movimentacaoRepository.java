package br.com.titan.estacionamento.repository;

import br.com.titan.estacionamento.model.movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface movimentacaoRepository extends JpaRepository<movimentacao, Long>{

}

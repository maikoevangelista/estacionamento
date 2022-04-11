package br.com.titan.estacionamento.repository;

import br.com.titan.estacionamento.model.Preco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrecoRepository extends JpaRepository<Preco, String>{

}

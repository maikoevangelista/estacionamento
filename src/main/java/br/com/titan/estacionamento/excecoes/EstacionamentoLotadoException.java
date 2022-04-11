package br.com.titan.estacionamento.excecoes;

@SuppressWarnings("serial")
public class EstacionamentoLotadoException extends RuntimeException{

	public EstacionamentoLotadoException(String msg) {
		super(msg);
	}
}

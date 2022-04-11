package br.com.titan.estacionamento.excecoes;

@SuppressWarnings("serial")
public class CapacidadeTotalException extends RuntimeException{
	
	public CapacidadeTotalException(String msg) {
		super(msg);
	}
}

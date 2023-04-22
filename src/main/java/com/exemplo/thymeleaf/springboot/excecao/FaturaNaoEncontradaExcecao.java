package com.exemplo.thymeleaf.springboot.excecao;

public class FaturaNaoEncontradaExcecao extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FaturaNaoEncontradaExcecao() {
		super();
	}

	public FaturaNaoEncontradaExcecao(String mensagemPersonalizada) {
		super(mensagemPersonalizada);
	}
}
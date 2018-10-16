package br.edu.unichristus.livrariadata.excecoes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Livro não encontrado")
public class LivroNaoEncontradoException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2991735267985791574L;

	public LivroNaoEncontradoException(Long id) {
		super("Livro " + id + " não encontrado.");
		
	}

	
	
	
	
}

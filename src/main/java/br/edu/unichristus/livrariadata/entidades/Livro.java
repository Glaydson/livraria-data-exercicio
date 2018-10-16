package br.edu.unichristus.livrariadata.entidades;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Table(name = "TB_LIVRO")
@Data
public class Livro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long livroID;

	@NotBlank
	private String titulo;

	@Min(value = 50)
	private int numeroPaginas;

	private BigDecimal preco;
	
	// Por simplificação, definimos que cada livro só tem um autor 
	// Livro é o lado dominante, deve incluir seu autor
	@ManyToOne
	private Autor autor;

	public Livro(String titulo, int numeroPaginas, BigDecimal preco, Autor autor) {
		super();
		this.titulo = titulo;
		this.numeroPaginas = numeroPaginas;
		this.preco = preco;
		this.autor = autor;
	}
	
	public Livro(String titulo, int numeroPaginas, BigDecimal preco) {
		super();
		this.titulo = titulo;
		this.numeroPaginas = numeroPaginas;
		this.preco = preco;
	}
	
	public Livro() {

	}

	@Override
	public String toString() {
		return "Livro [livroID=" + livroID + ", titulo=" + titulo + ", numeroPaginas=" + numeroPaginas + ", preco="
				+ preco + ", autor= " + autor.getNome() + "]";
	}
}

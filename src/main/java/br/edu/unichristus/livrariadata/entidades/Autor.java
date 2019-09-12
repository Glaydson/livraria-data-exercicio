package br.edu.unichristus.livrariadata.entidades;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name="TB_AUTOR")
@Data
public class Autor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long autorID;
	
	private String nome;
	
	private String paisOrigem;
	
	// Autor eh incluido pelo seu livro
	@JsonIgnoreProperties("autor")
	@OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Livro> livros;
	
	public Autor(String nome, String paisOrigem) {
		this.nome = nome;
		this.paisOrigem = paisOrigem;
	}
	
	public Autor() {};
	
	
	

}

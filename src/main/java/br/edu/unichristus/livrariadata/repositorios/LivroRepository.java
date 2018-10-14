package br.edu.unichristus.livrariadata.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unichristus.livrariadata.entidades.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

	List<Livro> findByTituloContains(String titulo);

}

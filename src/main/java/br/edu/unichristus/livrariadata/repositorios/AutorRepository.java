package br.edu.unichristus.livrariadata.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unichristus.livrariadata.entidades.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {

}

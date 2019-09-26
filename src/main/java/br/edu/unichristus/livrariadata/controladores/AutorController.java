package br.edu.unichristus.livrariadata.controladores;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unichristus.livrariadata.entidades.Autor;
import br.edu.unichristus.livrariadata.repositorios.AutorRepository;

@RestController
@RequestMapping("/autor")
public class AutorController {
	
	@Autowired
	private AutorRepository repoAutor;
	
	@GetMapping("/todos")
	public List<Autor> buscarTodos() {
		return repoAutor.findAll();
	}
	
	@GetMapping("nomes/pais")
	public List<String> buscarPeloPais(@PathVariable String pais) {
		List<Autor> todosAutores = repoAutor.findAll();
		return todosAutores.stream()
			.filter(a -> a.getPaisOrigem().equals(pais))
			.map(Autor::getNome)
			.collect(Collectors.toList());
	}

}

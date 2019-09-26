package br.edu.unichristus.livrariadata.controladores;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unichristus.livrariadata.entidades.Livro;
import br.edu.unichristus.livrariadata.excecoes.LivroNaoEncontradoException;
import br.edu.unichristus.livrariadata.repositorios.LivroRepository;

@RestController
@RequestMapping("/livro")
public class LivroController {

	@Autowired
	private LivroRepository repoLivro;


	@GetMapping("/todos")
	public List<Livro> buscarTodos() {
		return repoLivro.findAll();
	}

	@GetMapping("/{id}")
	public Livro buscarPeloID(@PathVariable Long id) {
		Optional<Livro> livroOptional = repoLivro.findById(id);
		livroOptional.orElseThrow(() -> new LivroNaoEncontradoException(id));
		return livroOptional.get();
	}

	@PostMapping("/novo")
	public Livro salvar(@RequestBody Livro livro) {
		//System.out.println(livro);
		return repoLivro.save(new Livro(livro.getTitulo(), livro.getNumeroPaginas(), livro.getPreco()));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteLivro(@PathVariable("id") long id) {
		System.out.println("Delete Livro com ID = " + id + "...");
 		repoLivro.deleteById(id);
 		return new ResponseEntity<>("Livro foi removido!", HttpStatus.OK);
	}
	
	@GetMapping("/titulo/{titulo}")
	public List<Livro> buscarPeloTitulo(@PathVariable String titulo) {
 
		List<Livro> livros = repoLivro.findByTituloContains(titulo);
		return livros;
	}
	
	@GetMapping("/titulosAutoresPais")
	public List<String> buscarPeloPais(@PathVariable String pais) {
		List<Livro> todosLivros = repoLivro.findAll();
		Stream<String> streamLivros = todosLivros.stream()
			.filter(livro -> livro.getAutor().getPaisOrigem().equals(pais))
			.map(Livro :: getTitulo);
		return streamLivros.collect(Collectors.toList());
	}
	
	@GetMapping("/precoMedio")
	public double obterPrecoMedioLivros() {
		List<Livro> todosLivros = repoLivro.findAll();
		return todosLivros.stream()
				.mapToDouble(livro -> livro.getPreco().doubleValue())
				.average()
				.getAsDouble();
	}
	
}


package br.edu.unichristus.livrariadata.controladores;

import java.util.List;

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
import br.edu.unichristus.livrariadata.repositorios.LivroRepository;

@CrossOrigin(origins = "http://localhost:8080")
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
		return repoLivro.findById(id).get();
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

}

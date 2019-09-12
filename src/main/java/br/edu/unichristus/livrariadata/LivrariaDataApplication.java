package br.edu.unichristus.livrariadata;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.unichristus.livrariadata.entidades.Autor;
import br.edu.unichristus.livrariadata.entidades.Livro;
import br.edu.unichristus.livrariadata.repositorios.AutorRepository;
import br.edu.unichristus.livrariadata.repositorios.LivroRepository;

@SpringBootApplication
public class LivrariaDataApplication implements CommandLineRunner {
	
	@Autowired
	private LivroRepository repoLivros;
	
	@Autowired
	private AutorRepository repoAutores;
	
	public static void main(String[] args) {
		SpringApplication.run(LivrariaDataApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		// limpa os reposit³rios
		repoLivros.deleteAll();
		repoAutores.deleteAll();
		// salva alguns livros e autores
		Autor stephenking = new Autor("Stephen King", "Estados Unidos");
		Autor gillianflynn = new Autor("Gillian Flynn", "Estados Unidos");
		Autor jorgeamado = new Autor("Jorge Amado", "Brasil");
		Autor gracilianoramos = new Autor("Graciliano Ramos", "Brasil");
		Autor conandoyle = new Autor("Arthur Conan Doyle", "Inglaterra");
		
		repoAutores.save(stephenking);
		repoAutores.save(gillianflynn);
		repoAutores.save(jorgeamado);
		repoAutores.save(gracilianoramos);
		repoAutores.save(conandoyle);
		
		repoLivros.save(new Livro("", 20, new BigDecimal(72.90), stephenking));
		repoLivros.save(new Livro("A Torre Negra - O Pistoleiro", 123, new BigDecimal(37.90), stephenking));
		repoLivros.save(new Livro("Objetos Cortantes", 490, new BigDecimal(26.31), gillianflynn));
		repoLivros.save(new Livro("Capitaes da Areia", 500, new BigDecimal(35.90), jorgeamado));
		repoLivros.save(new Livro("Dona Flor e Seus Dois Maridos", 350, new BigDecimal(78.50), jorgeamado));
		repoLivros.save(new Livro("Tieta do Agreste", 600, new BigDecimal(90.00), jorgeamado));
		repoLivros.save(new Livro("Vidas Secas", 850, new BigDecimal(190.00), gracilianoramos));
		repoLivros.save(new Livro("Memorias do Carcere", 320, new BigDecimal(55.00), gracilianoramos));
		repoLivros.save(new Livro("O Mundo Perdido", 110, new BigDecimal(25.00), conandoyle));
		repoLivros.save(new Livro("As Aventuras de Sherlock Holmes", 420, new BigDecimal(124.00), conandoyle));
		repoLivros.save(new Livro("O Cao dos Baskervilles", 210, new BigDecimal(92.00), conandoyle));
		
		// Obtem todos os livros
		System.out.println("obtendo os livros...");
		System.out.println("-------------------------");
		repoLivros.findAll().stream().forEach(System.out::println);
		System.out.println();
		
		// Livro pelo ID
		this.repoLivros.findById(40L);
		
		// EXERCICIO
		
		// 2 - Livros dos autores de um determinado pais (somente os titulos de cada livro)
		System.out.println("LIVROS DE AUTORES BRASILEIROS:");
		
		
		// 3 - Preco medio dos livros cadastrados
		System.out.println();
		System.out.print("PRECO MEDIO DOS LIVROS: ");
		
		
		// 4 - Listar os NOMES dos autores de um determinado pais
		System.out.println();
		System.out.println("AUTORES DOS ESTADOS UNIDOS:");
		
		
		// 6 - Listar autores com seus livros - Listagem contendo nome de cada autor autor e o titulo dos seus livros
		System.out.println();
		System.out.println("LISTA DE AUTORES COM SEUS RESPECTIVOS LIVROS:");

		
		
		
	}
}

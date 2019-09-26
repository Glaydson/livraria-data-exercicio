package br.edu.unichristus.livrariadata;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.unichristus.livrariadata.controladores.AutorController;
import br.edu.unichristus.livrariadata.controladores.LivroController;
import br.edu.unichristus.livrariadata.entidades.Autor;
import br.edu.unichristus.livrariadata.entidades.Editora;
import br.edu.unichristus.livrariadata.entidades.Livro;
import br.edu.unichristus.livrariadata.repositorios.AutorRepository;
import br.edu.unichristus.livrariadata.repositorios.EditoraRepository;
import br.edu.unichristus.livrariadata.repositorios.LivroRepository;

@SpringBootApplication
public class LivrariaDataApplication implements CommandLineRunner {
	
	@Autowired
	private LivroRepository repoLivros;

	@Autowired
	private AutorRepository repoAutores;
	
	@Autowired
	private EditoraRepository repoEditoras;
	
	@Autowired
	private LivroController controleLivros;
	
	@Autowired
	private AutorController controleAutores;
	
	
	public static void main(String[] args) {
		SpringApplication.run(LivrariaDataApplication.class,
				args);
	}

	@Override
	public void run(String... args) throws Exception {
		// limpa os repositorios
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

		repoLivros.save(new Livro("A Torre Negra - O Pistoleiro", 123,
				new BigDecimal(37.90), stephenking));
		repoLivros.save(new Livro("Objetos Cortantes", 490,
				new BigDecimal(26.31), gillianflynn));
		repoLivros.save(new Livro("Capitaes da Areia", 500,
				new BigDecimal(35.90), jorgeamado));
		repoLivros.save(new Livro("Dona Flor e Seus Dois Maridos", 350,
				new BigDecimal(78.50), jorgeamado));
		repoLivros.save(new Livro("Tieta do Agreste", 600,
				new BigDecimal(90.00), jorgeamado));
		repoLivros.save(new Livro("Vidas Secas", 850, new BigDecimal(190.00),
				gracilianoramos));
		repoLivros.save(new Livro("Memorias do Carcere", 320,
				new BigDecimal(55.00), gracilianoramos));
		repoLivros.save(new Livro("O Mundo Perdido", 110, new BigDecimal(25.00),
				conandoyle));
		repoLivros.save(new Livro("As Aventuras de Sherlock Holmes", 420,
				new BigDecimal(124.00), conandoyle));
		repoLivros.save(new Livro("O Cao dos Baskervilles", 210,
				new BigDecimal(92.00), conandoyle));
		
		// Grava algumas editoras
		repoEditoras.save(new Editora("Grupo A", "Porto Alegre", 1973));
		repoEditoras.save(new Editora("Clássica", "Rio de Janeiro", 1854));
		repoEditoras.save(new Editora("Paulinas", "Curitiba", 1960));
		repoEditoras.save(new Editora("Saraiva", "São Paulo", 1914));
		repoEditoras.save(new Editora("Campus", "Rio de Janeiro", 1976));
		repoEditoras.save(new Editora("Círculo do Livro", "São Paulo", 1973));
		repoEditoras.save(new Editora("Makron Books", "São Paulo", 1990));
		repoEditoras.save(new Editora("Rocco", "Rio de Janeiro", 1975));
		repoEditoras.save(new Editora("LPM", "Porto Alegre", 1974));
		repoEditoras.save(new Editora("CEPE", "Recife", 1967));
		repoEditoras.save(new Editora("Nova Fronteira", "Rio de Janeiro", 1965));

		// Obtem todos os livros
		System.out.println("obtendo os livros...");
		System.out.println("-------------------------");
		repoLivros.findAll().stream().forEach(System.out::println);
		System.out.println();

		// EXERCICIOS

		// 2 - Livros dos autores de um determinado pais (somente os titulos de
		// cada livro)
		System.out.println("EXERCÍCIO 2 - LIVROS DE AUTORES BRASILEIROS:");
		controleLivros.buscarPeloPais("Brasil").forEach(System.out::println);

		// 3 - Preco medio dos livros cadastrados
		System.out.println();
		System.out.print("EXERCÍCIO 3 - PRECO MEDIO DOS LIVROS: ");
		System.out.printf("Preço Médio dos Livros: %.2f%n", controleLivros.obterPrecoMedioLivros());

		// 4 - Listar os NOMES dos autores de um determinado pais (após criar o
		// método em AutorController)
		System.out.println();
		System.out.println("EXERCÍCIO 4 - AUTORES DOS ESTADOS UNIDOS:");
		controleAutores.buscarPeloPais("Estados Unidos").forEach(System.out::println);

		// 6 - Listar autores com seus livros - Listagem contendo nome de cada
		// autor autor e o titulo dos seus livros
		System.out.println();
		System.out.println(
				"EXERCÍCIO 6 - LISTA DE AUTORES COM SEUS RESPECTIVOS LIVROS:");
		controleAutores.buscarTodos()
			.forEach((a) -> {
				System.out.println(a.getNome());
				a.getLivros()
					.forEach(livro -> System.out.printf("      %s%n",livro.getTitulo()));
			}
			);
		
		// *********** EXERCÍCIOS 2a CHANCE ***********
		
		// 2 - TODAS AS EDITORAS DO RIO DE JANEIRO
		System.out.println();
		System.out.println("EXERCÍCIO 2 - EDITORAS DA CIDADE DO RIO DE JANEIRO");
		
		
		// 3 - EDITORAS FUNDADAS ENTRE 1970 E 1990
		System.out.println();
		System.out.println("EXERCÍCIO 3 - EDITORAS FUNDADAS ENTRE 1970 E 1990");

		
		// 4 - ANO DA EDITORA MAIS ANTIGA
		System.out.println();
		System.out.println("EXERCÍCIO 4 - ANO DA EDITORA MAIS ANTIGA");

		
		// 6 - EDITORAS AGRUPADAS POR CIDADE
		System.out.println();
		System.out.println("EXERCÍCIO 6 - LISTA DE EDITORAS AGRUPADAS POR CIDADE");
		
	}
}


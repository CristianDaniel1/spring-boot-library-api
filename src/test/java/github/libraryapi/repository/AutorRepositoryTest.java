package github.libraryapi.repository;

import github.libraryapi.entities.Autor;
import github.libraryapi.entities.GeneroLivro;
import github.libraryapi.entities.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest(){
        Autor autor = new Autor();
        autor.setNome("maria");
        autor.setNacionalidade("BRASILIANa");
        autor.setDataNascimento(LocalDate.of(1951, 1, 31));

        var autorSalvo = repository.save(autor);
        System.out.println("Autor: " + autorSalvo);
    }

    @Test
    public void atualizarTest(){
        var id = UUID.fromString("");

        Optional<Autor> possivelAutor = repository.findById(id);

        if (possivelAutor.isPresent()) {
            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do autor: ");
            System.out.println(autorEncontrado);

            autorEncontrado.setDataNascimento(LocalDate.of(1940, 1, 30));

            repository.save(autorEncontrado);
        }
    }

    @Test
    public void listarTest(){
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest(){
        System.out.println("Contagem de autores: " + repository.count());
    }

    @Test
    public void deletePorIdTest(){
        var id = UUID.fromString("2449f4e4-ee1a-4a71-8aa3-e9d46306fe8a");
        repository.deleteById(id);
    }

    @Test
    public void deleteTest(){
        var id = UUID.fromString("abc082bf-1d23-4767-b3d9-9f322856ca6a");
        var maria = repository.findById(id).get();
        repository.delete(maria);
    }

    @Test
    void salvarAutorComLivrosTest(){
        Autor autor = new Autor();
        autor.setNome("maria");
        autor.setNacionalidade("brasileira");
        autor.setDataNascimento(LocalDate.of(1970, 7, 6));

        Livro livro = new Livro();
        livro.setIsbn("45887-83442");
        livro.setPreco(BigDecimal.valueOf(345));
        livro.setGenero(GeneroLivro.MISTERIO);
        livro.setTitulo("Roubo a casa");
        livro.setDataPublicacao(LocalDate.of(1999, 1, 2));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("23455-83442");
        livro2.setPreco(BigDecimal.valueOf(111));
        livro2.setGenero(GeneroLivro.CIENCIA);
        livro2.setTitulo("OUTRO Roubo a casa");
        livro2.setDataPublicacao(LocalDate.of(2000, 1, 2));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        repository.save(autor);

        livroRepository.saveAll(autor.getLivros());
    }

    @Test
    void listarLivrosAutor(){
        var id = UUID.fromString("375e3a2a-2f34-432c-a621-95899b6e1a68");
        var autor = repository.findById(id).get();

        // buscar os livros do autor

        List<Livro> livrosLista = livroRepository.findByAutor(autor);
        autor.setLivros(livrosLista);

        autor.getLivros().forEach(System.out::println);
    }
}

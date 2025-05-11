package github.libraryapi.service;

import github.libraryapi.entity.GeneroLivro;
import github.libraryapi.repository.LivroRepository;
import github.libraryapi.entity.Livro;
import github.libraryapi.repository.specs.LivroSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static github.libraryapi.repository.specs.LivroSpecs.*;

@Service
@RequiredArgsConstructor
public class LivroService {
    private final LivroRepository repository;

    public Livro salvar(Livro livro) {
        return repository.save(livro);
    }

    public Optional<Livro> obterPorId(UUID id) {
        return repository.findById(id);
    }

    public void deletar(Livro livro) {
        repository.delete(livro);
    }

    public List<Livro> pesquisa(
            String isbn, String titulo, String nomeAutor, GeneroLivro genero, Integer anoPublicacao) {

//        Specification<Livro> specs = Specification
//                .where(LivroSpecs.isbnEqual(isbn))
//                .and(LivroSpecs.tituloLike(titulo))
//                .and(LivroSpecs.generoEqual(genero));

        // select * from livro where 0 = 0
        Specification<Livro> specs = Specification.where((root, query, cb) -> cb.conjunction());

        if (isbn != null)
            specs = specs.and(isbnEqual(isbn));

        if (titulo != null)
            specs = specs.and(tituloLike(titulo));

        if (genero != null)
            specs = specs.and(generoEqual(genero));

        if (anoPublicacao != null)
            specs = specs.and(anoPublicacaoEqual(anoPublicacao));

        if (nomeAutor != null)
            specs = specs.and(nomeAutorLike(nomeAutor));

        return repository.findAll(specs);
    }

    public void atualizar(Livro livro) {
        if (livro.getId() == null) {
            throw new IllegalArgumentException("Para atualizar, é necessário que o livro já esteja salvo na base.");
        }

        repository.save(livro);
    }
}

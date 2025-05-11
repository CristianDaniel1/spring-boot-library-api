package github.libraryapi.mappers;

import github.libraryapi.repository.AutorRepository;
import github.libraryapi.dto.CadastroLivroDTO;
import github.libraryapi.dto.ResultadoPesquisaLivroDTO;
import github.libraryapi.entities.Livro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = AutorMapper.class)
public abstract class LivroMapper {

    @Autowired
    AutorRepository autorRepository;

    @Mapping(target = "autor", expression = "java( autorRepository.findById(dto.idAutor()).orElse(null) )")
    public abstract Livro toEntity(CadastroLivroDTO dto);

    public abstract ResultadoPesquisaLivroDTO toDTO(Livro livro);
}

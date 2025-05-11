package github.libraryapi.mappers;

import github.libraryapi.dto.AutorDTO;
import github.libraryapi.entities.Autor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AutorMapper {
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "dataNascimento", target = "dataNascimento")
    Autor toEntity(AutorDTO dto);

    AutorDTO toDTO(Autor autor);
}

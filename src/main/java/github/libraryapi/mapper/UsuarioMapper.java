package github.libraryapi.mapper;

import github.libraryapi.dto.UsuarioDTO;
import github.libraryapi.entity.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    Usuario toEntity(UsuarioDTO dto);

    Usuario toDTO(Usuario usuario);

}

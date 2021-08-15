package bf.e_fixell_backoffice.service.mapper;


import bf.e_fixell_backoffice.domain.*;
import bf.e_fixell_backoffice.service.dto.TypeFraisDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TypeFrais} and its DTO {@link TypeFraisDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TypeFraisMapper extends EntityMapper<TypeFraisDTO, TypeFrais> {


    @Mapping(target = "frais", ignore = true)
    @Mapping(target = "removeFrais", ignore = true)
    TypeFrais toEntity(TypeFraisDTO typeFraisDTO);

    default TypeFrais fromId(Long id) {
        if (id == null) {
            return null;
        }
        TypeFrais typeFrais = new TypeFrais();
        typeFrais.setId(id);
        return typeFrais;
    }
}

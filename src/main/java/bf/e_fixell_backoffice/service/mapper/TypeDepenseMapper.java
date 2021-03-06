package bf.e_fixell_backoffice.service.mapper;


import bf.e_fixell_backoffice.domain.*;
import bf.e_fixell_backoffice.service.dto.TypeDepenseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TypeDepense} and its DTO {@link TypeDepenseDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TypeDepenseMapper extends EntityMapper<TypeDepenseDTO, TypeDepense> {


    @Mapping(target = "depenses", ignore = true)
    @Mapping(target = "removeDepense", ignore = true)
    TypeDepense toEntity(TypeDepenseDTO typeDepenseDTO);

    default TypeDepense fromId(Long id) {
        if (id == null) {
            return null;
        }
        TypeDepense typeDepense = new TypeDepense();
        typeDepense.setId(id);
        return typeDepense;
    }
}

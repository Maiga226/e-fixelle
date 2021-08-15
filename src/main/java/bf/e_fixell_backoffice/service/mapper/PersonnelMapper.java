package bf.e_fixell_backoffice.service.mapper;


import bf.e_fixell_backoffice.domain.*;
import bf.e_fixell_backoffice.service.dto.PersonnelDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Personnel} and its DTO {@link PersonnelDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PersonnelMapper extends EntityMapper<PersonnelDTO, Personnel> {


    @Mapping(target = "historiqueAffectations", ignore = true)
    @Mapping(target = "removeHistoriqueAffectation", ignore = true)
    Personnel toEntity(PersonnelDTO personnelDTO);

    default Personnel fromId(Long id) {
        if (id == null) {
            return null;
        }
        Personnel personnel = new Personnel();
        personnel.setId(id);
        return personnel;
    }
}

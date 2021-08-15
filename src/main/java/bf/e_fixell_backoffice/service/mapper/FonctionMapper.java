package bf.e_fixell_backoffice.service.mapper;


import bf.e_fixell_backoffice.domain.*;
import bf.e_fixell_backoffice.service.dto.FonctionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Fonction} and its DTO {@link FonctionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FonctionMapper extends EntityMapper<FonctionDTO, Fonction> {


    @Mapping(target = "historiqueAffectations", ignore = true)
    @Mapping(target = "removeHistoriqueAffectation", ignore = true)
    Fonction toEntity(FonctionDTO fonctionDTO);

    default Fonction fromId(Long id) {
        if (id == null) {
            return null;
        }
        Fonction fonction = new Fonction();
        fonction.setId(id);
        return fonction;
    }
}

package bf.e_fixell_backoffice.service.mapper;


import bf.e_fixell_backoffice.domain.*;
import bf.e_fixell_backoffice.service.dto.FicheTechniqueDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FicheTechnique} and its DTO {@link FicheTechniqueDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FicheTechniqueMapper extends EntityMapper<FicheTechniqueDTO, FicheTechnique> {


    @Mapping(target = "caracteristiques", ignore = true)
    @Mapping(target = "removeCaracteristique", ignore = true)
    FicheTechnique toEntity(FicheTechniqueDTO ficheTechniqueDTO);

    default FicheTechnique fromId(Long id) {
        if (id == null) {
            return null;
        }
        FicheTechnique ficheTechnique = new FicheTechnique();
        ficheTechnique.setId(id);
        return ficheTechnique;
    }
}

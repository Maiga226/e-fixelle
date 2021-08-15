package bf.e_fixell_backoffice.service.mapper;


import bf.e_fixell_backoffice.domain.*;
import bf.e_fixell_backoffice.service.dto.UniteOrganisationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link UniteOrganisation} and its DTO {@link UniteOrganisationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UniteOrganisationMapper extends EntityMapper<UniteOrganisationDTO, UniteOrganisation> {


    @Mapping(target = "fournisseurs", ignore = true)
    @Mapping(target = "removeFournisseur", ignore = true)
    UniteOrganisation toEntity(UniteOrganisationDTO uniteOrganisationDTO);

    default UniteOrganisation fromId(Long id) {
        if (id == null) {
            return null;
        }
        UniteOrganisation uniteOrganisation = new UniteOrganisation();
        uniteOrganisation.setId(id);
        return uniteOrganisation;
    }
}

package bf.e_fixell_backoffice.service.mapper;


import bf.e_fixell_backoffice.domain.*;
import bf.e_fixell_backoffice.service.dto.CaisseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Caisse} and its DTO {@link CaisseDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CaisseMapper extends EntityMapper<CaisseDTO, Caisse> {


    @Mapping(target = "sessionCaisses", ignore = true)
    @Mapping(target = "removeSessionCaisse", ignore = true)
    Caisse toEntity(CaisseDTO caisseDTO);

    default Caisse fromId(Long id) {
        if (id == null) {
            return null;
        }
        Caisse caisse = new Caisse();
        caisse.setId(id);
        return caisse;
    }
}

package bf.e_fixell_backoffice.service.mapper;


import bf.e_fixell_backoffice.domain.*;
import bf.e_fixell_backoffice.service.dto.SessionCaisseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SessionCaisse} and its DTO {@link SessionCaisseDTO}.
 */
@Mapper(componentModel = "spring", uses = {CaisseMapper.class})
public interface SessionCaisseMapper extends EntityMapper<SessionCaisseDTO, SessionCaisse> {

    @Mapping(source = "caisse.id", target = "caisseId")
    SessionCaisseDTO toDto(SessionCaisse sessionCaisse);

    @Mapping(target = "paiements", ignore = true)
    @Mapping(target = "removePaiement", ignore = true)
    @Mapping(target = "depenses", ignore = true)
    @Mapping(target = "removeDepense", ignore = true)
    @Mapping(source = "caisseId", target = "caisse")
    SessionCaisse toEntity(SessionCaisseDTO sessionCaisseDTO);

    default SessionCaisse fromId(Long id) {
        if (id == null) {
            return null;
        }
        SessionCaisse sessionCaisse = new SessionCaisse();
        sessionCaisse.setId(id);
        return sessionCaisse;
    }
}

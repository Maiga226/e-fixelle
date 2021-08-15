package bf.e_fixell_backoffice.service.mapper;


import bf.e_fixell_backoffice.domain.*;
import bf.e_fixell_backoffice.service.dto.VenteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Vente} and its DTO {@link VenteDTO}.
 */
@Mapper(componentModel = "spring", uses = {ClientMapper.class})
public interface VenteMapper extends EntityMapper<VenteDTO, Vente> {

    @Mapping(source = "client.id", target = "clientId")
    VenteDTO toDto(Vente vente);

    @Mapping(target = "transactions", ignore = true)
    @Mapping(target = "removeTransaction", ignore = true)
    @Mapping(target = "paiements", ignore = true)
    @Mapping(target = "removePaiement", ignore = true)
    @Mapping(source = "clientId", target = "client")
    Vente toEntity(VenteDTO venteDTO);

    default Vente fromId(Long id) {
        if (id == null) {
            return null;
        }
        Vente vente = new Vente();
        vente.setId(id);
        return vente;
    }
}

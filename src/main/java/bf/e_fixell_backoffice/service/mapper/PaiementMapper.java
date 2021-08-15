package bf.e_fixell_backoffice.service.mapper;


import bf.e_fixell_backoffice.domain.*;
import bf.e_fixell_backoffice.service.dto.PaiementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Paiement} and its DTO {@link PaiementDTO}.
 */
@Mapper(componentModel = "spring", uses = {CommandeMapper.class, VenteMapper.class, SessionCaisseMapper.class})
public interface PaiementMapper extends EntityMapper<PaiementDTO, Paiement> {

    @Mapping(source = "commande.id", target = "commandeId")
    @Mapping(source = "vente.id", target = "venteId")
    @Mapping(source = "sessioncaisse.id", target = "sessioncaisseId")
    PaiementDTO toDto(Paiement paiement);

    @Mapping(source = "commandeId", target = "commande")
    @Mapping(source = "venteId", target = "vente")
    @Mapping(source = "sessioncaisseId", target = "sessioncaisse")
    Paiement toEntity(PaiementDTO paiementDTO);

    default Paiement fromId(Long id) {
        if (id == null) {
            return null;
        }
        Paiement paiement = new Paiement();
        paiement.setId(id);
        return paiement;
    }
}

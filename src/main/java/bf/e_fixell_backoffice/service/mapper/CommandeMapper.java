package bf.e_fixell_backoffice.service.mapper;


import bf.e_fixell_backoffice.domain.*;
import bf.e_fixell_backoffice.service.dto.CommandeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Commande} and its DTO {@link CommandeDTO}.
 */
@Mapper(componentModel = "spring", uses = {FournisseurMapper.class, ClientMapper.class})
public interface CommandeMapper extends EntityMapper<CommandeDTO, Commande> {

    @Mapping(source = "fournisseur.id", target = "fournisseurId")
    @Mapping(source = "client.id", target = "clientId")
    CommandeDTO toDto(Commande commande);

    @Mapping(target = "transactions", ignore = true)
    @Mapping(target = "removeTransaction", ignore = true)
    @Mapping(target = "livraisons", ignore = true)
    @Mapping(target = "removeLivraison", ignore = true)
    @Mapping(target = "paiements", ignore = true)
    @Mapping(target = "removePaiement", ignore = true)
    @Mapping(target = "frais", ignore = true)
    @Mapping(target = "removeFrais", ignore = true)
    @Mapping(source = "fournisseurId", target = "fournisseur")
    @Mapping(source = "clientId", target = "client")
    Commande toEntity(CommandeDTO commandeDTO);

    default Commande fromId(Long id) {
        if (id == null) {
            return null;
        }
        Commande commande = new Commande();
        commande.setId(id);
        return commande;
    }
}

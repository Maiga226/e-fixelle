package bf.e_fixell_backoffice.service.mapper;


import bf.e_fixell_backoffice.domain.*;
import bf.e_fixell_backoffice.service.dto.LivraisonDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Livraison} and its DTO {@link LivraisonDTO}.
 */
@Mapper(componentModel = "spring", uses = {CommandeMapper.class, FournisseurMapper.class, TransportMapper.class})
public interface LivraisonMapper extends EntityMapper<LivraisonDTO, Livraison> {

    @Mapping(source = "commande.id", target = "commandeId")
    @Mapping(source = "fournisseur.id", target = "fournisseurId")
    @Mapping(source = "transport.id", target = "transportId")
    LivraisonDTO toDto(Livraison livraison);

    @Mapping(target = "transactions", ignore = true)
    @Mapping(target = "removeTransaction", ignore = true)
    @Mapping(target = "frais", ignore = true)
    @Mapping(target = "removeFrais", ignore = true)
    @Mapping(source = "commandeId", target = "commande")
    @Mapping(source = "fournisseurId", target = "fournisseur")
    @Mapping(source = "transportId", target = "transport")
    Livraison toEntity(LivraisonDTO livraisonDTO);

    default Livraison fromId(Long id) {
        if (id == null) {
            return null;
        }
        Livraison livraison = new Livraison();
        livraison.setId(id);
        return livraison;
    }
}

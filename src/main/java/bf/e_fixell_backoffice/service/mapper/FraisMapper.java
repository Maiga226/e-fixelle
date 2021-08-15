package bf.e_fixell_backoffice.service.mapper;


import bf.e_fixell_backoffice.domain.*;
import bf.e_fixell_backoffice.service.dto.FraisDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Frais} and its DTO {@link FraisDTO}.
 */
@Mapper(componentModel = "spring", uses = {CommandeMapper.class, TypeFraisMapper.class, LivraisonMapper.class})
public interface FraisMapper extends EntityMapper<FraisDTO, Frais> {

    @Mapping(source = "commande.id", target = "commandeId")
    @Mapping(source = "typeFrais.id", target = "typeFraisId")
    @Mapping(source = "livraison.id", target = "livraisonId")
    FraisDTO toDto(Frais frais);

    @Mapping(source = "commandeId", target = "commande")
    @Mapping(source = "typeFraisId", target = "typeFrais")
    @Mapping(source = "livraisonId", target = "livraison")
    Frais toEntity(FraisDTO fraisDTO);

    default Frais fromId(Long id) {
        if (id == null) {
            return null;
        }
        Frais frais = new Frais();
        frais.setId(id);
        return frais;
    }
}

package bf.e_fixell_backoffice.service.mapper;


import bf.e_fixell_backoffice.domain.*;
import bf.e_fixell_backoffice.service.dto.PrixProduitDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PrixProduit} and its DTO {@link PrixProduitDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProduitMapper.class})
public interface PrixProduitMapper extends EntityMapper<PrixProduitDTO, PrixProduit> {

    @Mapping(source = "produit.id", target = "produitId")
    PrixProduitDTO toDto(PrixProduit prixProduit);

    @Mapping(source = "produitId", target = "produit")
    PrixProduit toEntity(PrixProduitDTO prixProduitDTO);

    default PrixProduit fromId(Long id) {
        if (id == null) {
            return null;
        }
        PrixProduit prixProduit = new PrixProduit();
        prixProduit.setId(id);
        return prixProduit;
    }
}

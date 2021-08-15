package bf.e_fixell_backoffice.service.mapper;


import bf.e_fixell_backoffice.domain.*;
import bf.e_fixell_backoffice.service.dto.ProduitDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Produit} and its DTO {@link ProduitDTO}.
 */
@Mapper(componentModel = "spring", uses = {CategorieMapper.class, ClassificationMapper.class})
public interface ProduitMapper extends EntityMapper<ProduitDTO, Produit> {

    @Mapping(source = "categorie.id", target = "categorieId")
    @Mapping(source = "classification.id", target = "classificationId")
    ProduitDTO toDto(Produit produit);

    @Mapping(target = "prixProduits", ignore = true)
    @Mapping(target = "removePrixProduit", ignore = true)
    @Mapping(target = "pertes", ignore = true)
    @Mapping(target = "removePerte", ignore = true)
    @Mapping(target = "transactions", ignore = true)
    @Mapping(target = "removeTransaction", ignore = true)
    @Mapping(source = "categorieId", target = "categorie")
    @Mapping(source = "classificationId", target = "classification")
    Produit toEntity(ProduitDTO produitDTO);

    default Produit fromId(Long id) {
        if (id == null) {
            return null;
        }
        Produit produit = new Produit();
        produit.setId(id);
        return produit;
    }
}

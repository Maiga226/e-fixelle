package bf.e_fixell_backoffice.service.mapper;


import bf.e_fixell_backoffice.domain.*;
import bf.e_fixell_backoffice.service.dto.CategorieDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Categorie} and its DTO {@link CategorieDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CategorieMapper extends EntityMapper<CategorieDTO, Categorie> {


    @Mapping(target = "produits", ignore = true)
    @Mapping(target = "removeProduit", ignore = true)
    Categorie toEntity(CategorieDTO categorieDTO);

    default Categorie fromId(Long id) {
        if (id == null) {
            return null;
        }
        Categorie categorie = new Categorie();
        categorie.setId(id);
        return categorie;
    }
}

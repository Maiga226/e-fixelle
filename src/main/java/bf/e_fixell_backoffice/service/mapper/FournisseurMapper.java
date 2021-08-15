package bf.e_fixell_backoffice.service.mapper;


import bf.e_fixell_backoffice.domain.*;
import bf.e_fixell_backoffice.service.dto.FournisseurDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Fournisseur} and its DTO {@link FournisseurDTO}.
 */
@Mapper(componentModel = "spring", uses = {UniteOrganisationMapper.class})
public interface FournisseurMapper extends EntityMapper<FournisseurDTO, Fournisseur> {

    @Mapping(source = "adresse.id", target = "adresseId")
    FournisseurDTO toDto(Fournisseur fournisseur);

    @Mapping(target = "commandes", ignore = true)
    @Mapping(target = "removeCommande", ignore = true)
    @Mapping(target = "approvisionnements", ignore = true)
    @Mapping(target = "removeApprovisionnement", ignore = true)
    @Mapping(target = "livraisons", ignore = true)
    @Mapping(target = "removeLivraison", ignore = true)
    @Mapping(source = "adresseId", target = "adresse")
    Fournisseur toEntity(FournisseurDTO fournisseurDTO);

    default Fournisseur fromId(Long id) {
        if (id == null) {
            return null;
        }
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(id);
        return fournisseur;
    }
}

package bf.e_fixell_backoffice.service.mapper;


import bf.e_fixell_backoffice.domain.*;
import bf.e_fixell_backoffice.service.dto.ApprovisionnementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Approvisionnement} and its DTO {@link ApprovisionnementDTO}.
 */
@Mapper(componentModel = "spring", uses = {FournisseurMapper.class})
public interface ApprovisionnementMapper extends EntityMapper<ApprovisionnementDTO, Approvisionnement> {

    @Mapping(source = "fournisseur.id", target = "fournisseurId")
    ApprovisionnementDTO toDto(Approvisionnement approvisionnement);

    @Mapping(target = "transactions", ignore = true)
    @Mapping(target = "removeTransaction", ignore = true)
    @Mapping(source = "fournisseurId", target = "fournisseur")
    Approvisionnement toEntity(ApprovisionnementDTO approvisionnementDTO);

    default Approvisionnement fromId(Long id) {
        if (id == null) {
            return null;
        }
        Approvisionnement approvisionnement = new Approvisionnement();
        approvisionnement.setId(id);
        return approvisionnement;
    }
}

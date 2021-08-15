package bf.e_fixell_backoffice.service.mapper;


import bf.e_fixell_backoffice.domain.*;
import bf.e_fixell_backoffice.service.dto.PerteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Perte} and its DTO {@link PerteDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProduitMapper.class})
public interface PerteMapper extends EntityMapper<PerteDTO, Perte> {

    @Mapping(source = "produit.id", target = "produitId")
    PerteDTO toDto(Perte perte);

    @Mapping(source = "produitId", target = "produit")
    Perte toEntity(PerteDTO perteDTO);

    default Perte fromId(Long id) {
        if (id == null) {
            return null;
        }
        Perte perte = new Perte();
        perte.setId(id);
        return perte;
    }
}

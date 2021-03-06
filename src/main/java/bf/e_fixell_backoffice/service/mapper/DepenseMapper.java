package bf.e_fixell_backoffice.service.mapper;


import bf.e_fixell_backoffice.domain.*;
import bf.e_fixell_backoffice.service.dto.DepenseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Depense} and its DTO {@link DepenseDTO}.
 */
@Mapper(componentModel = "spring", uses = {TypeDepenseMapper.class, SessionCaisseMapper.class})
public interface DepenseMapper extends EntityMapper<DepenseDTO, Depense> {

    @Mapping(source = "typeDepense.id", target = "typeDepenseId")
    @Mapping(source = "sessionCaisse.id", target = "sessionCaisseId")
    DepenseDTO toDto(Depense depense);

    @Mapping(source = "typeDepenseId", target = "typeDepense")
    @Mapping(source = "sessionCaisseId", target = "sessionCaisse")
    Depense toEntity(DepenseDTO depenseDTO);

    default Depense fromId(Long id) {
        if (id == null) {
            return null;
        }
        Depense depense = new Depense();
        depense.setId(id);
        return depense;
    }
}

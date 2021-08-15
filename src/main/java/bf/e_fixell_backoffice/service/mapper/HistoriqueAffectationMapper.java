package bf.e_fixell_backoffice.service.mapper;


import bf.e_fixell_backoffice.domain.*;
import bf.e_fixell_backoffice.service.dto.HistoriqueAffectationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link HistoriqueAffectation} and its DTO {@link HistoriqueAffectationDTO}.
 */
@Mapper(componentModel = "spring", uses = {PersonnelMapper.class, FonctionMapper.class})
public interface HistoriqueAffectationMapper extends EntityMapper<HistoriqueAffectationDTO, HistoriqueAffectation> {

    @Mapping(source = "personnel.id", target = "personnelId")
    @Mapping(source = "fonction.id", target = "fonctionId")
    HistoriqueAffectationDTO toDto(HistoriqueAffectation historiqueAffectation);

    @Mapping(source = "personnelId", target = "personnel")
    @Mapping(source = "fonctionId", target = "fonction")
    HistoriqueAffectation toEntity(HistoriqueAffectationDTO historiqueAffectationDTO);

    default HistoriqueAffectation fromId(Long id) {
        if (id == null) {
            return null;
        }
        HistoriqueAffectation historiqueAffectation = new HistoriqueAffectation();
        historiqueAffectation.setId(id);
        return historiqueAffectation;
    }
}

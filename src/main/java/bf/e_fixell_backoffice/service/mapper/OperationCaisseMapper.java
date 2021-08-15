package bf.e_fixell_backoffice.service.mapper;


import bf.e_fixell_backoffice.domain.*;
import bf.e_fixell_backoffice.service.dto.OperationCaisseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link OperationCaisse} and its DTO {@link OperationCaisseDTO}.
 */
@Mapper(componentModel = "spring", uses = {CaisseMapper.class})
public interface OperationCaisseMapper extends EntityMapper<OperationCaisseDTO, OperationCaisse> {

    @Mapping(source = "caisseSrc.id", target = "caisseSrcId")
    @Mapping(source = "caisseDst.id", target = "caisseDstId")
    OperationCaisseDTO toDto(OperationCaisse operationCaisse);

    @Mapping(source = "caisseSrcId", target = "caisseSrc")
    @Mapping(source = "caisseDstId", target = "caisseDst")
    OperationCaisse toEntity(OperationCaisseDTO operationCaisseDTO);

    default OperationCaisse fromId(Long id) {
        if (id == null) {
            return null;
        }
        OperationCaisse operationCaisse = new OperationCaisse();
        operationCaisse.setId(id);
        return operationCaisse;
    }
}

package bf.e_fixell_backoffice.service.mapper;


import bf.e_fixell_backoffice.domain.*;
import bf.e_fixell_backoffice.service.dto.SocieteTransportDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SocieteTransport} and its DTO {@link SocieteTransportDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SocieteTransportMapper extends EntityMapper<SocieteTransportDTO, SocieteTransport> {


    @Mapping(target = "transports", ignore = true)
    @Mapping(target = "removeTransport", ignore = true)
    SocieteTransport toEntity(SocieteTransportDTO societeTransportDTO);

    default SocieteTransport fromId(Long id) {
        if (id == null) {
            return null;
        }
        SocieteTransport societeTransport = new SocieteTransport();
        societeTransport.setId(id);
        return societeTransport;
    }
}

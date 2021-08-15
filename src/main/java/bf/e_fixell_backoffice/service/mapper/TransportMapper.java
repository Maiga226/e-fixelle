package bf.e_fixell_backoffice.service.mapper;


import bf.e_fixell_backoffice.domain.*;
import bf.e_fixell_backoffice.service.dto.TransportDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Transport} and its DTO {@link TransportDTO}.
 */
@Mapper(componentModel = "spring", uses = {SocieteTransportMapper.class})
public interface TransportMapper extends EntityMapper<TransportDTO, Transport> {

    @Mapping(source = "societeTransport.id", target = "societeTransportId")
    TransportDTO toDto(Transport transport);

    @Mapping(target = "livraisons", ignore = true)
    @Mapping(target = "removeLivraison", ignore = true)
    @Mapping(source = "societeTransportId", target = "societeTransport")
    Transport toEntity(TransportDTO transportDTO);

    default Transport fromId(Long id) {
        if (id == null) {
            return null;
        }
        Transport transport = new Transport();
        transport.setId(id);
        return transport;
    }
}

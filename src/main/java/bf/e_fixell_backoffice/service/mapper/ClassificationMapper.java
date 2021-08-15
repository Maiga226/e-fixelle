package bf.e_fixell_backoffice.service.mapper;


import bf.e_fixell_backoffice.domain.*;
import bf.e_fixell_backoffice.service.dto.ClassificationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Classification} and its DTO {@link ClassificationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ClassificationMapper extends EntityMapper<ClassificationDTO, Classification> {


    @Mapping(target = "produits", ignore = true)
    @Mapping(target = "removeProduit", ignore = true)
    Classification toEntity(ClassificationDTO classificationDTO);

    default Classification fromId(Long id) {
        if (id == null) {
            return null;
        }
        Classification classification = new Classification();
        classification.setId(id);
        return classification;
    }
}

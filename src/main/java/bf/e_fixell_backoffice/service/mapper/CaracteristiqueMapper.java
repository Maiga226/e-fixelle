package bf.e_fixell_backoffice.service.mapper;


import bf.e_fixell_backoffice.domain.*;
import bf.e_fixell_backoffice.service.dto.CaracteristiqueDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Caracteristique} and its DTO {@link CaracteristiqueDTO}.
 */
@Mapper(componentModel = "spring", uses = {FicheTechniqueMapper.class})
public interface CaracteristiqueMapper extends EntityMapper<CaracteristiqueDTO, Caracteristique> {

    @Mapping(source = "ficheTechnique.id", target = "ficheTechniqueId")
    CaracteristiqueDTO toDto(Caracteristique caracteristique);

    @Mapping(source = "ficheTechniqueId", target = "ficheTechnique")
    Caracteristique toEntity(CaracteristiqueDTO caracteristiqueDTO);

    default Caracteristique fromId(Long id) {
        if (id == null) {
            return null;
        }
        Caracteristique caracteristique = new Caracteristique();
        caracteristique.setId(id);
        return caracteristique;
    }
}

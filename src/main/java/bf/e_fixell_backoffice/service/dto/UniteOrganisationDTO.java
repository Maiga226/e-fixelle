package bf.e_fixell_backoffice.service.dto;

import java.io.Serializable;
import bf.e_fixell_backoffice.domain.enumeration.TypeUniteOrganisation;

/**
 * A DTO for the {@link bf.e_fixell_backoffice.domain.UniteOrganisation} entity.
 */
public class UniteOrganisationDTO implements Serializable {
    
    private Long id;

    private String libelle;

    private TypeUniteOrganisation type;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public TypeUniteOrganisation getType() {
        return type;
    }

    public void setType(TypeUniteOrganisation type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UniteOrganisationDTO)) {
            return false;
        }

        return id != null && id.equals(((UniteOrganisationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UniteOrganisationDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
}

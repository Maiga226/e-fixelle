package bf.e_fixell_backoffice.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link bf.e_fixell_backoffice.domain.TypeFrais} entity.
 */
public class TypeFraisDTO implements Serializable {
    
    private Long id;

    private String libelle;

    
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TypeFraisDTO)) {
            return false;
        }

        return id != null && id.equals(((TypeFraisDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TypeFraisDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}

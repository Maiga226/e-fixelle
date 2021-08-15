package bf.e_fixell_backoffice.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link bf.e_fixell_backoffice.domain.Fonction} entity.
 */
public class FonctionDTO implements Serializable {
    
    private Long id;

    private String libelle;

    private String description;

    private Float salaireMin;

    private Float salaireMax;

    
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getSalaireMin() {
        return salaireMin;
    }

    public void setSalaireMin(Float salaireMin) {
        this.salaireMin = salaireMin;
    }

    public Float getSalaireMax() {
        return salaireMax;
    }

    public void setSalaireMax(Float salaireMax) {
        this.salaireMax = salaireMax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FonctionDTO)) {
            return false;
        }

        return id != null && id.equals(((FonctionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FonctionDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            ", salaireMin=" + getSalaireMin() +
            ", salaireMax=" + getSalaireMax() +
            "}";
    }
}

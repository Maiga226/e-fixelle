package bf.e_fixell_backoffice.service.dto;

import bf.e_fixell_backoffice.domain.Caracteristique;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * A DTO for the {@link bf.e_fixell_backoffice.domain.FicheTechnique} entity.
 */
public class FicheTechniqueDTO implements Serializable {

    private Long id;

    private String libelle;

    private Set<Caracteristique> caracteristiques;


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

    public Set<Caracteristique> getCaracteristiques() {
        return caracteristiques;
    }

    public void setCaracteristiques(Set<Caracteristique> caracteristiques) {
        this.caracteristiques = caracteristiques;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FicheTechniqueDTO)) {
            return false;
        }

        return id != null && id.equals(((FicheTechniqueDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FicheTechniqueDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}

package bf.e_fixell_backoffice.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link bf.e_fixell_backoffice.domain.Caracteristique} entity.
 */
public class CaracteristiqueDTO implements Serializable {
    
    private Long id;

    private String libelle;

    private String valeur;


    private Long ficheTechniqueId;
    
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

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public Long getFicheTechniqueId() {
        return ficheTechniqueId;
    }

    public void setFicheTechniqueId(Long ficheTechniqueId) {
        this.ficheTechniqueId = ficheTechniqueId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CaracteristiqueDTO)) {
            return false;
        }

        return id != null && id.equals(((CaracteristiqueDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CaracteristiqueDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", valeur='" + getValeur() + "'" +
            ", ficheTechniqueId=" + getFicheTechniqueId() +
            "}";
    }
}

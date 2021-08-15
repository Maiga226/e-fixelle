package bf.e_fixell_backoffice.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import bf.e_fixell_backoffice.domain.enumeration.Statut;
import bf.e_fixell_backoffice.domain.enumeration.TypeCaisse;

/**
 * A DTO for the {@link bf.e_fixell_backoffice.domain.Caisse} entity.
 */
public class CaisseDTO implements Serializable {
    
    private Long id;

    private String code;

    private String libelle;

    private BigDecimal sommeMin;

    private BigDecimal sommeMax;

    private BigDecimal somme;

    private Statut statut;

    private TypeCaisse typeCaisse;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public BigDecimal getSommeMin() {
        return sommeMin;
    }

    public void setSommeMin(BigDecimal sommeMin) {
        this.sommeMin = sommeMin;
    }

    public BigDecimal getSommeMax() {
        return sommeMax;
    }

    public void setSommeMax(BigDecimal sommeMax) {
        this.sommeMax = sommeMax;
    }

    public BigDecimal getSomme() {
        return somme;
    }

    public void setSomme(BigDecimal somme) {
        this.somme = somme;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public TypeCaisse getTypeCaisse() {
        return typeCaisse;
    }

    public void setTypeCaisse(TypeCaisse typeCaisse) {
        this.typeCaisse = typeCaisse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CaisseDTO)) {
            return false;
        }

        return id != null && id.equals(((CaisseDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CaisseDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", sommeMin=" + getSommeMin() +
            ", sommeMax=" + getSommeMax() +
            ", somme=" + getSomme() +
            ", statut='" + getStatut() + "'" +
            ", typeCaisse='" + getTypeCaisse() + "'" +
            "}";
    }
}

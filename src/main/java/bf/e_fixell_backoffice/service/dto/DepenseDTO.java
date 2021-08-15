package bf.e_fixell_backoffice.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link bf.e_fixell_backoffice.domain.Depense} entity.
 */
public class DepenseDTO implements Serializable {
    
    private Long id;

    private String libelle;

    private String description;

    private BigDecimal montant;


    private Long typeDepenseId;

    private Long sessionCaisseId;
    
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

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public Long getTypeDepenseId() {
        return typeDepenseId;
    }

    public void setTypeDepenseId(Long typeDepenseId) {
        this.typeDepenseId = typeDepenseId;
    }

    public Long getSessionCaisseId() {
        return sessionCaisseId;
    }

    public void setSessionCaisseId(Long sessionCaisseId) {
        this.sessionCaisseId = sessionCaisseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DepenseDTO)) {
            return false;
        }

        return id != null && id.equals(((DepenseDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DepenseDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            ", montant=" + getMontant() +
            ", typeDepenseId=" + getTypeDepenseId() +
            ", sessionCaisseId=" + getSessionCaisseId() +
            "}";
    }
}

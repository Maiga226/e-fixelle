package bf.e_fixell_backoffice.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.math.BigDecimal;
import bf.e_fixell_backoffice.domain.enumeration.Statut;

/**
 * A DTO for the {@link bf.e_fixell_backoffice.domain.PrixProduit} entity.
 */
public class PrixProduitDTO implements Serializable {
    
    private Long id;

    private Instant dateDebut;

    private Instant dateFin;

    private BigDecimal prix;

    private Statut statut;


    private Long produitId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Instant dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Instant getDateFin() {
        return dateFin;
    }

    public void setDateFin(Instant dateFin) {
        this.dateFin = dateFin;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public Long getProduitId() {
        return produitId;
    }

    public void setProduitId(Long produitId) {
        this.produitId = produitId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PrixProduitDTO)) {
            return false;
        }

        return id != null && id.equals(((PrixProduitDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PrixProduitDTO{" +
            "id=" + getId() +
            ", dateDebut='" + getDateDebut() + "'" +
            ", dateFin='" + getDateFin() + "'" +
            ", prix=" + getPrix() +
            ", statut='" + getStatut() + "'" +
            ", produitId=" + getProduitId() +
            "}";
    }
}

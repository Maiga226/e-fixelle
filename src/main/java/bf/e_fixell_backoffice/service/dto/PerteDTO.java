package bf.e_fixell_backoffice.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link bf.e_fixell_backoffice.domain.Perte} entity.
 */
public class PerteDTO implements Serializable {
    
    private Long id;

    private String libelle;

    private Instant date;

    private Integer quantite;

    private BigDecimal montant;


    private Long produitId;
    
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

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
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
        if (!(o instanceof PerteDTO)) {
            return false;
        }

        return id != null && id.equals(((PerteDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PerteDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", date='" + getDate() + "'" +
            ", quantite=" + getQuantite() +
            ", montant=" + getMontant() +
            ", produitId=" + getProduitId() +
            "}";
    }
}

package bf.e_fixell_backoffice.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link bf.e_fixell_backoffice.domain.Frais} entity.
 */
public class FraisDTO implements Serializable {
    
    private Long id;

    private String libelle;

    private BigDecimal valeur;


    private Long commandeId;

    private Long typeFraisId;

    private Long livraisonId;
    
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

    public BigDecimal getValeur() {
        return valeur;
    }

    public void setValeur(BigDecimal valeur) {
        this.valeur = valeur;
    }

    public Long getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(Long commandeId) {
        this.commandeId = commandeId;
    }

    public Long getTypeFraisId() {
        return typeFraisId;
    }

    public void setTypeFraisId(Long typeFraisId) {
        this.typeFraisId = typeFraisId;
    }

    public Long getLivraisonId() {
        return livraisonId;
    }

    public void setLivraisonId(Long livraisonId) {
        this.livraisonId = livraisonId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FraisDTO)) {
            return false;
        }

        return id != null && id.equals(((FraisDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FraisDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", valeur=" + getValeur() +
            ", commandeId=" + getCommandeId() +
            ", typeFraisId=" + getTypeFraisId() +
            ", livraisonId=" + getLivraisonId() +
            "}";
    }
}

package bf.e_fixell_backoffice.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.math.BigDecimal;
import bf.e_fixell_backoffice.domain.enumeration.Etat;

/**
 * A DTO for the {@link bf.e_fixell_backoffice.domain.Commande} entity.
 */
public class CommandeDTO implements Serializable {
    
    private Long id;

    private String code;

    private String libelle;

    private Instant date;

    private BigDecimal somme;

    private Instant dateLivraisonPrevu;

    private Etat etat;

    private String motif;

    private BigDecimal avance;

    private Boolean avanceEnPercent;


    private Long fournisseurId;

    private Long clientId;
    
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

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public BigDecimal getSomme() {
        return somme;
    }

    public void setSomme(BigDecimal somme) {
        this.somme = somme;
    }

    public Instant getDateLivraisonPrevu() {
        return dateLivraisonPrevu;
    }

    public void setDateLivraisonPrevu(Instant dateLivraisonPrevu) {
        this.dateLivraisonPrevu = dateLivraisonPrevu;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public BigDecimal getAvance() {
        return avance;
    }

    public void setAvance(BigDecimal avance) {
        this.avance = avance;
    }

    public Boolean isAvanceEnPercent() {
        return avanceEnPercent;
    }

    public void setAvanceEnPercent(Boolean avanceEnPercent) {
        this.avanceEnPercent = avanceEnPercent;
    }

    public Long getFournisseurId() {
        return fournisseurId;
    }

    public void setFournisseurId(Long fournisseurId) {
        this.fournisseurId = fournisseurId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommandeDTO)) {
            return false;
        }

        return id != null && id.equals(((CommandeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CommandeDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", date='" + getDate() + "'" +
            ", somme=" + getSomme() +
            ", dateLivraisonPrevu='" + getDateLivraisonPrevu() + "'" +
            ", etat='" + getEtat() + "'" +
            ", motif='" + getMotif() + "'" +
            ", avance=" + getAvance() +
            ", avanceEnPercent='" + isAvanceEnPercent() + "'" +
            ", fournisseurId=" + getFournisseurId() +
            ", clientId=" + getClientId() +
            "}";
    }
}

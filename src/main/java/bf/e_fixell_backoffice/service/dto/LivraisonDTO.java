package bf.e_fixell_backoffice.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.math.BigDecimal;
import bf.e_fixell_backoffice.domain.enumeration.Etat;

/**
 * A DTO for the {@link bf.e_fixell_backoffice.domain.Livraison} entity.
 */
public class LivraisonDTO implements Serializable {
    
    private Long id;

    private String code;

    private String libelle;

    private Instant date;

    private BigDecimal somme;

    private Etat etat;

    private String motif;


    private Long commandeId;

    private Long fournisseurId;

    private Long transportId;
    
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

    public Long getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(Long commandeId) {
        this.commandeId = commandeId;
    }

    public Long getFournisseurId() {
        return fournisseurId;
    }

    public void setFournisseurId(Long fournisseurId) {
        this.fournisseurId = fournisseurId;
    }

    public Long getTransportId() {
        return transportId;
    }

    public void setTransportId(Long transportId) {
        this.transportId = transportId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LivraisonDTO)) {
            return false;
        }

        return id != null && id.equals(((LivraisonDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LivraisonDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", date='" + getDate() + "'" +
            ", somme=" + getSomme() +
            ", etat='" + getEtat() + "'" +
            ", motif='" + getMotif() + "'" +
            ", commandeId=" + getCommandeId() +
            ", fournisseurId=" + getFournisseurId() +
            ", transportId=" + getTransportId() +
            "}";
    }
}

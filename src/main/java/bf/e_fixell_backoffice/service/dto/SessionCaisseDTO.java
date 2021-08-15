package bf.e_fixell_backoffice.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.math.BigDecimal;
import bf.e_fixell_backoffice.domain.enumeration.Statut;

/**
 * A DTO for the {@link bf.e_fixell_backoffice.domain.SessionCaisse} entity.
 */
public class SessionCaisseDTO implements Serializable {
    
    private Long id;

    private Long code;

    private String libelle;

    private Instant dateDebut;

    private Instant dateFin;

    private BigDecimal sommeDebut;

    private BigDecimal sommeFin;

    private BigDecimal depassement;

    private BigDecimal manquant;

    private Statut statut;


    private Long caisseId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
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

    public BigDecimal getSommeDebut() {
        return sommeDebut;
    }

    public void setSommeDebut(BigDecimal sommeDebut) {
        this.sommeDebut = sommeDebut;
    }

    public BigDecimal getSommeFin() {
        return sommeFin;
    }

    public void setSommeFin(BigDecimal sommeFin) {
        this.sommeFin = sommeFin;
    }

    public BigDecimal getDepassement() {
        return depassement;
    }

    public void setDepassement(BigDecimal depassement) {
        this.depassement = depassement;
    }

    public BigDecimal getManquant() {
        return manquant;
    }

    public void setManquant(BigDecimal manquant) {
        this.manquant = manquant;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public Long getCaisseId() {
        return caisseId;
    }

    public void setCaisseId(Long caisseId) {
        this.caisseId = caisseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SessionCaisseDTO)) {
            return false;
        }

        return id != null && id.equals(((SessionCaisseDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SessionCaisseDTO{" +
            "id=" + getId() +
            ", code=" + getCode() +
            ", libelle='" + getLibelle() + "'" +
            ", dateDebut='" + getDateDebut() + "'" +
            ", dateFin='" + getDateFin() + "'" +
            ", sommeDebut=" + getSommeDebut() +
            ", sommeFin=" + getSommeFin() +
            ", depassement=" + getDepassement() +
            ", manquant=" + getManquant() +
            ", statut='" + getStatut() + "'" +
            ", caisseId=" + getCaisseId() +
            "}";
    }
}

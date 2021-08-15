package bf.e_fixell_backoffice.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link bf.e_fixell_backoffice.domain.HistoriqueAffectation} entity.
 */
public class HistoriqueAffectationDTO implements Serializable {
    
    private Long id;

    private Instant dateDebut;

    private Instant dateFin;

    private BigDecimal salaire;


    private Long personnelId;

    private Long fonctionId;
    
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

    public BigDecimal getSalaire() {
        return salaire;
    }

    public void setSalaire(BigDecimal salaire) {
        this.salaire = salaire;
    }

    public Long getPersonnelId() {
        return personnelId;
    }

    public void setPersonnelId(Long personnelId) {
        this.personnelId = personnelId;
    }

    public Long getFonctionId() {
        return fonctionId;
    }

    public void setFonctionId(Long fonctionId) {
        this.fonctionId = fonctionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HistoriqueAffectationDTO)) {
            return false;
        }

        return id != null && id.equals(((HistoriqueAffectationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HistoriqueAffectationDTO{" +
            "id=" + getId() +
            ", dateDebut='" + getDateDebut() + "'" +
            ", dateFin='" + getDateFin() + "'" +
            ", salaire=" + getSalaire() +
            ", personnelId=" + getPersonnelId() +
            ", fonctionId=" + getFonctionId() +
            "}";
    }
}

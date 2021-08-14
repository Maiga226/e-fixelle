package bf.e_fixell_backoffice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * A HistoriqueAffectation.
 */
@Entity
@Table(name = "historique_affectation")
public class HistoriqueAffectation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "date_debut")
    private Instant dateDebut;

    @Column(name = "date_fin")
    private Instant dateFin;

    @Column(name = "salaire", precision = 21, scale = 2)
    private BigDecimal salaire;

    @ManyToOne
    @JsonIgnoreProperties(value = "historiqueAffectations", allowSetters = true)
    private Personnel personnel;

    @ManyToOne
    @JsonIgnoreProperties(value = "historiqueAffectations", allowSetters = true)
    private Fonction fonction;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateDebut() {
        return dateDebut;
    }

    public HistoriqueAffectation dateDebut(Instant dateDebut) {
        this.dateDebut = dateDebut;
        return this;
    }

    public void setDateDebut(Instant dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Instant getDateFin() {
        return dateFin;
    }

    public HistoriqueAffectation dateFin(Instant dateFin) {
        this.dateFin = dateFin;
        return this;
    }

    public void setDateFin(Instant dateFin) {
        this.dateFin = dateFin;
    }

    public BigDecimal getSalaire() {
        return salaire;
    }

    public HistoriqueAffectation salaire(BigDecimal salaire) {
        this.salaire = salaire;
        return this;
    }

    public void setSalaire(BigDecimal salaire) {
        this.salaire = salaire;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public HistoriqueAffectation personnel(Personnel personnel) {
        this.personnel = personnel;
        return this;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public Fonction getFonction() {
        return fonction;
    }

    public HistoriqueAffectation fonction(Fonction fonction) {
        this.fonction = fonction;
        return this;
    }

    public void setFonction(Fonction fonction) {
        this.fonction = fonction;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HistoriqueAffectation)) {
            return false;
        }
        return id != null && id.equals(((HistoriqueAffectation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HistoriqueAffectation{" +
            "id=" + getId() +
            ", dateDebut='" + getDateDebut() + "'" +
            ", dateFin='" + getDateFin() + "'" +
            ", salaire=" + getSalaire() +
            "}";
    }
}

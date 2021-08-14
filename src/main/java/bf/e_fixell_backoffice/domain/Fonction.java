package bf.e_fixell_backoffice.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Fonction.
 */
@Entity
@Table(name = "fonction")
public class Fonction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "description")
    private String description;

    @Column(name = "salaire_min")
    private Float salaireMin;

    @Column(name = "salaire_max")
    private Float salaireMax;

    @OneToMany(mappedBy = "fonction")
    private Set<HistoriqueAffectation> historiqueAffectations = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public Fonction libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public Fonction description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getSalaireMin() {
        return salaireMin;
    }

    public Fonction salaireMin(Float salaireMin) {
        this.salaireMin = salaireMin;
        return this;
    }

    public void setSalaireMin(Float salaireMin) {
        this.salaireMin = salaireMin;
    }

    public Float getSalaireMax() {
        return salaireMax;
    }

    public Fonction salaireMax(Float salaireMax) {
        this.salaireMax = salaireMax;
        return this;
    }

    public void setSalaireMax(Float salaireMax) {
        this.salaireMax = salaireMax;
    }

    public Set<HistoriqueAffectation> getHistoriqueAffectations() {
        return historiqueAffectations;
    }

    public Fonction historiqueAffectations(Set<HistoriqueAffectation> historiqueAffectations) {
        this.historiqueAffectations = historiqueAffectations;
        return this;
    }

    public Fonction addHistoriqueAffectation(HistoriqueAffectation historiqueAffectation) {
        this.historiqueAffectations.add(historiqueAffectation);
        historiqueAffectation.setFonction(this);
        return this;
    }

    public Fonction removeHistoriqueAffectation(HistoriqueAffectation historiqueAffectation) {
        this.historiqueAffectations.remove(historiqueAffectation);
        historiqueAffectation.setFonction(null);
        return this;
    }

    public void setHistoriqueAffectations(Set<HistoriqueAffectation> historiqueAffectations) {
        this.historiqueAffectations = historiqueAffectations;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Fonction)) {
            return false;
        }
        return id != null && id.equals(((Fonction) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Fonction{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            ", salaireMin=" + getSalaireMin() +
            ", salaireMax=" + getSalaireMax() +
            "}";
    }
}

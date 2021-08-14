package bf.e_fixell_backoffice.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * A Personnel.
 */
@Entity
@Table(name = "personnel")
public class Personnel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "matricule")
    private String matricule;

    @Column(name = "salaire", precision = 21, scale = 2)
    private BigDecimal salaire;

    @OneToMany(mappedBy = "personnel")
    private Set<HistoriqueAffectation> historiqueAffectations = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public Personnel nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Personnel prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public Personnel telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMatricule() {
        return matricule;
    }

    public Personnel matricule(String matricule) {
        this.matricule = matricule;
        return this;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public BigDecimal getSalaire() {
        return salaire;
    }

    public Personnel salaire(BigDecimal salaire) {
        this.salaire = salaire;
        return this;
    }

    public void setSalaire(BigDecimal salaire) {
        this.salaire = salaire;
    }

    public Set<HistoriqueAffectation> getHistoriqueAffectations() {
        return historiqueAffectations;
    }

    public Personnel historiqueAffectations(Set<HistoriqueAffectation> historiqueAffectations) {
        this.historiqueAffectations = historiqueAffectations;
        return this;
    }

    public Personnel addHistoriqueAffectation(HistoriqueAffectation historiqueAffectation) {
        this.historiqueAffectations.add(historiqueAffectation);
        historiqueAffectation.setPersonnel(this);
        return this;
    }

    public Personnel removeHistoriqueAffectation(HistoriqueAffectation historiqueAffectation) {
        this.historiqueAffectations.remove(historiqueAffectation);
        historiqueAffectation.setPersonnel(null);
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
        if (!(o instanceof Personnel)) {
            return false;
        }
        return id != null && id.equals(((Personnel) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Personnel{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", matricule='" + getMatricule() + "'" +
            ", salaire=" + getSalaire() +
            "}";
    }
}

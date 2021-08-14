package bf.e_fixell_backoffice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A Depense.
 */
@Entity
@Table(name = "depense")
public class Depense implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "description")
    private String description;

    @Column(name = "montant", precision = 21, scale = 2)
    private BigDecimal montant;

    @ManyToOne
    @JsonIgnoreProperties(value = "depenses", allowSetters = true)
    private TypeDepense typeDepense;

    @ManyToOne
    @JsonIgnoreProperties(value = "depenses", allowSetters = true)
    private SessionCaisse sessionCaisse;

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

    public Depense libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public Depense description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public Depense montant(BigDecimal montant) {
        this.montant = montant;
        return this;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public TypeDepense getTypeDepense() {
        return typeDepense;
    }

    public Depense typeDepense(TypeDepense typeDepense) {
        this.typeDepense = typeDepense;
        return this;
    }

    public void setTypeDepense(TypeDepense typeDepense) {
        this.typeDepense = typeDepense;
    }

    public SessionCaisse getSessionCaisse() {
        return sessionCaisse;
    }

    public Depense sessionCaisse(SessionCaisse sessionCaisse) {
        this.sessionCaisse = sessionCaisse;
        return this;
    }

    public void setSessionCaisse(SessionCaisse sessionCaisse) {
        this.sessionCaisse = sessionCaisse;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Depense)) {
            return false;
        }
        return id != null && id.equals(((Depense) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Depense{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            ", montant=" + getMontant() +
            "}";
    }
}

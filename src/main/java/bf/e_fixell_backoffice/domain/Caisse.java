package bf.e_fixell_backoffice.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import bf.e_fixell_backoffice.domain.enumeration.Statut;

import bf.e_fixell_backoffice.domain.enumeration.TypeCaisse;

/**
 * A Caisse.
 */
@Entity
@Table(name = "caisse")
public class Caisse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "somme_min", precision = 21, scale = 2)
    private BigDecimal sommeMin;

    @Column(name = "somme_max", precision = 21, scale = 2)
    private BigDecimal sommeMax;

    @Column(name = "somme", precision = 21, scale = 2)
    private BigDecimal somme;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut")
    private Statut statut;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_caisse")
    private TypeCaisse typeCaisse;

    @OneToMany(mappedBy = "caisse")
    private Set<SessionCaisse> sessionCaisses = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public Caisse code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public Caisse libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public BigDecimal getSommeMin() {
        return sommeMin;
    }

    public Caisse sommeMin(BigDecimal sommeMin) {
        this.sommeMin = sommeMin;
        return this;
    }

    public void setSommeMin(BigDecimal sommeMin) {
        this.sommeMin = sommeMin;
    }

    public BigDecimal getSommeMax() {
        return sommeMax;
    }

    public Caisse sommeMax(BigDecimal sommeMax) {
        this.sommeMax = sommeMax;
        return this;
    }

    public void setSommeMax(BigDecimal sommeMax) {
        this.sommeMax = sommeMax;
    }

    public BigDecimal getSomme() {
        return somme;
    }

    public Caisse somme(BigDecimal somme) {
        this.somme = somme;
        return this;
    }

    public void setSomme(BigDecimal somme) {
        this.somme = somme;
    }

    public Statut getStatut() {
        return statut;
    }

    public Caisse statut(Statut statut) {
        this.statut = statut;
        return this;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public TypeCaisse getTypeCaisse() {
        return typeCaisse;
    }

    public Caisse typeCaisse(TypeCaisse typeCaisse) {
        this.typeCaisse = typeCaisse;
        return this;
    }

    public void setTypeCaisse(TypeCaisse typeCaisse) {
        this.typeCaisse = typeCaisse;
    }

    public Set<SessionCaisse> getSessionCaisses() {
        return sessionCaisses;
    }

    public Caisse sessionCaisses(Set<SessionCaisse> sessionCaisses) {
        this.sessionCaisses = sessionCaisses;
        return this;
    }

    public Caisse addSessionCaisse(SessionCaisse sessionCaisse) {
        this.sessionCaisses.add(sessionCaisse);
        sessionCaisse.setCaisse(this);
        return this;
    }

    public Caisse removeSessionCaisse(SessionCaisse sessionCaisse) {
        this.sessionCaisses.remove(sessionCaisse);
        sessionCaisse.setCaisse(null);
        return this;
    }

    public void setSessionCaisses(Set<SessionCaisse> sessionCaisses) {
        this.sessionCaisses = sessionCaisses;
    }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Caisse)) {
            return false;
        }
        return id != null && id.equals(((Caisse) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Caisse{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", sommeMin=" + getSommeMin() +
            ", sommeMax=" + getSommeMax() +
            ", somme=" + getSomme() +
            ", statut='" + getStatut() + "'" +
            ", typeCaisse='" + getTypeCaisse() + "'" +
            "}";
    }
}

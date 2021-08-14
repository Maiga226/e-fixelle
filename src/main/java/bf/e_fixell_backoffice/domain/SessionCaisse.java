package bf.e_fixell_backoffice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import bf.e_fixell_backoffice.domain.enumeration.Statut;

/**
 * A SessionCaisse.
 */
@Entity
@Table(name = "session_caisse")
public class SessionCaisse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "code")
    private Long code;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "date_debut")
    private Instant dateDebut;

    @Column(name = "date_fin")
    private Instant dateFin;

    @Column(name = "somme_debut", precision = 21, scale = 2)
    private BigDecimal sommeDebut;

    @Column(name = "somme_fin", precision = 21, scale = 2)
    private BigDecimal sommeFin;

    @Column(name = "depassement", precision = 21, scale = 2)
    private BigDecimal depassement;

    @Column(name = "manquant", precision = 21, scale = 2)
    private BigDecimal manquant;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut")
    private Statut statut;

    @OneToMany(mappedBy = "sessioncaisse")
    private Set<Paiement> paiements = new HashSet<>();

    @OneToMany(mappedBy = "sessionCaisse")
    private Set<Depense> depenses = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "sessionCaisses", allowSetters = true)
    private Caisse caisse;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCode() {
        return code;
    }

    public SessionCaisse code(Long code) {
        this.code = code;
        return this;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public SessionCaisse libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Instant getDateDebut() {
        return dateDebut;
    }

    public SessionCaisse dateDebut(Instant dateDebut) {
        this.dateDebut = dateDebut;
        return this;
    }

    public void setDateDebut(Instant dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Instant getDateFin() {
        return dateFin;
    }

    public SessionCaisse dateFin(Instant dateFin) {
        this.dateFin = dateFin;
        return this;
    }

    public void setDateFin(Instant dateFin) {
        this.dateFin = dateFin;
    }

    public BigDecimal getSommeDebut() {
        return sommeDebut;
    }

    public SessionCaisse sommeDebut(BigDecimal sommeDebut) {
        this.sommeDebut = sommeDebut;
        return this;
    }

    public void setSommeDebut(BigDecimal sommeDebut) {
        this.sommeDebut = sommeDebut;
    }

    public BigDecimal getSommeFin() {
        return sommeFin;
    }

    public SessionCaisse sommeFin(BigDecimal sommeFin) {
        this.sommeFin = sommeFin;
        return this;
    }

    public void setSommeFin(BigDecimal sommeFin) {
        this.sommeFin = sommeFin;
    }

    public BigDecimal getDepassement() {
        return depassement;
    }

    public SessionCaisse depassement(BigDecimal depassement) {
        this.depassement = depassement;
        return this;
    }

    public void setDepassement(BigDecimal depassement) {
        this.depassement = depassement;
    }

    public BigDecimal getManquant() {
        return manquant;
    }

    public SessionCaisse manquant(BigDecimal manquant) {
        this.manquant = manquant;
        return this;
    }

    public void setManquant(BigDecimal manquant) {
        this.manquant = manquant;
    }

    public Statut getStatut() {
        return statut;
    }

    public SessionCaisse statut(Statut statut) {
        this.statut = statut;
        return this;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public Set<Paiement> getPaiements() {
        return paiements;
    }

    public SessionCaisse paiements(Set<Paiement> paiements) {
        this.paiements = paiements;
        return this;
    }

    public SessionCaisse addPaiement(Paiement paiement) {
        this.paiements.add(paiement);
        paiement.setSessioncaisse(this);
        return this;
    }

    public SessionCaisse removePaiement(Paiement paiement) {
        this.paiements.remove(paiement);
        paiement.setSessioncaisse(null);
        return this;
    }

    public void setPaiements(Set<Paiement> paiements) {
        this.paiements = paiements;
    }

    public Set<Depense> getDepenses() {
        return depenses;
    }

    public SessionCaisse depenses(Set<Depense> depenses) {
        this.depenses = depenses;
        return this;
    }

    public SessionCaisse addDepense(Depense depense) {
        this.depenses.add(depense);
        depense.setSessionCaisse(this);
        return this;
    }

    public SessionCaisse removeDepense(Depense depense) {
        this.depenses.remove(depense);
        depense.setSessionCaisse(null);
        return this;
    }

    public void setDepenses(Set<Depense> depenses) {
        this.depenses = depenses;
    }

    public Caisse getCaisse() {
        return caisse;
    }

    public SessionCaisse caisse(Caisse caisse) {
        this.caisse = caisse;
        return this;
    }

    public void setCaisse(Caisse caisse) {
        this.caisse = caisse;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SessionCaisse)) {
            return false;
        }
        return id != null && id.equals(((SessionCaisse) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SessionCaisse{" +
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
            "}";
    }
}

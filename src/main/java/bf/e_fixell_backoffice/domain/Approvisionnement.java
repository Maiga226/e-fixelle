package bf.e_fixell_backoffice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A Approvisionnement.
 */
@Entity
@Table(name = "approvisionnement")
public class Approvisionnement extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "date")
    private Instant date;

    @Column(name = "montant", precision = 21, scale = 2)
    private BigDecimal montant;

    @Column(name = "deleted")
    private boolean deleted;

    @OneToMany(mappedBy = "approvisionnement")
    private Set<Transaction> transactions = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "approvisionnements", allowSetters = true)
    private Fournisseur fournisseur;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getCode() {
        return code;
    }

    public Approvisionnement code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public Approvisionnement libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Instant getDate() {
        return date;
    }

    public Approvisionnement date(Instant date) {
        this.date = date;
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public Approvisionnement montant(BigDecimal montant) {
        this.montant = montant;
        return this;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public Approvisionnement transactions(Set<Transaction> transactions) {
        this.transactions = transactions;
        return this;
    }

    public Approvisionnement addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        transaction.setApprovisionnement(this);
        return this;
    }

    public Approvisionnement removeTransaction(Transaction transaction) {
        this.transactions.remove(transaction);
        transaction.setApprovisionnement(null);
        return this;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public Approvisionnement fournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
        return this;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Approvisionnement)) {
            return false;
        }
        return id != null && id.equals(((Approvisionnement) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Approvisionnement{" +
            "id=" + id +
            ", code='" + code + '\'' +
            ", libelle='" + libelle + '\'' +
            ", date=" + date +
            ", montant=" + montant +
            ", deleted=" + deleted +
            ", transactions=" + transactions +
            ", fournisseur=" + fournisseur +
            '}';
    }
}

package bf.e_fixell_backoffice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A Vente.
 */
@Entity
@Table(name = "vente")
public class Vente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "code")
    private String code;

    @Column(name = "date")
    private Instant date;

    @Column(name = "montant", precision = 21, scale = 2)
    private BigDecimal montant;

    @Column(name = "reste", precision = 21, scale = 2)
    private BigDecimal reste;

    @Column(name = "solder")
    private Boolean solder;

    @OneToMany(mappedBy = "vente")
    private Set<Transaction> transactions = new HashSet<>();

    @OneToMany(mappedBy = "vente")
    private Set<Paiement> paiements = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "ventes", allowSetters = true)
    private Client client;

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

    public Vente libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getCode() {
        return code;
    }

    public Vente code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Instant getDate() {
        return date;
    }

    public Vente date(Instant date) {
        this.date = date;
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public Vente montant(BigDecimal montant) {
        this.montant = montant;
        return this;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public BigDecimal getReste() {
        return reste;
    }

    public Vente reste(BigDecimal reste) {
        this.reste = reste;
        return this;
    }

    public void setReste(BigDecimal reste) {
        this.reste = reste;
    }

    public Boolean isSolder() {
        return solder;
    }

    public Vente solder(Boolean solder) {
        this.solder = solder;
        return this;
    }

    public void setSolder(Boolean solder) {
        this.solder = solder;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public Vente transactions(Set<Transaction> transactions) {
        this.transactions = transactions;
        return this;
    }

    public Vente addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        transaction.setVente(this);
        return this;
    }

    public Vente removeTransaction(Transaction transaction) {
        this.transactions.remove(transaction);
        transaction.setVente(null);
        return this;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Set<Paiement> getPaiements() {
        return paiements;
    }

    public Vente paiements(Set<Paiement> paiements) {
        this.paiements = paiements;
        return this;
    }

    public Vente addPaiement(Paiement paiement) {
        this.paiements.add(paiement);
        paiement.setVente(this);
        return this;
    }

    public Vente removePaiement(Paiement paiement) {
        this.paiements.remove(paiement);
        paiement.setVente(null);
        return this;
    }

    public void setPaiements(Set<Paiement> paiements) {
        this.paiements = paiements;
    }

    public Client getClient() {
        return client;
    }

    public Vente client(Client client) {
        this.client = client;
        return this;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vente)) {
            return false;
        }
        return id != null && id.equals(((Vente) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Vente{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", code='" + getCode() + "'" +
            ", date='" + getDate() + "'" +
            ", montant=" + getMontant() +
            ", reste=" + getReste() +
            ", solder='" + isSolder() + "'" +
            "}";
    }
}

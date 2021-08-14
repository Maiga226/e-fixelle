package bf.e_fixell_backoffice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import bf.e_fixell_backoffice.domain.enumeration.Etat;

/**
 * A Livraison.
 */
@Entity
@Table(name = "livraison")
public class Livraison implements Serializable {

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

    @Column(name = "somme", precision = 21, scale = 2)
    private BigDecimal somme;

    @Enumerated(EnumType.STRING)
    @Column(name = "etat")
    private Etat etat;

    @Column(name = "motif")
    private String motif;

    @OneToMany(mappedBy = "livraison")
    private Set<Transaction> transactions = new HashSet<>();

    @OneToMany(mappedBy = "livraison")
    private Set<Frais> frais = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "livraisons", allowSetters = true)
    private Commande commande;

    @ManyToOne
    @JsonIgnoreProperties(value = "livraisons", allowSetters = true)
    private Fournisseur fournisseur;

    @ManyToOne
    @JsonIgnoreProperties(value = "livraisons", allowSetters = true)
    private Transport transport;

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

    public Livraison code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public Livraison libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Instant getDate() {
        return date;
    }

    public Livraison date(Instant date) {
        this.date = date;
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public BigDecimal getSomme() {
        return somme;
    }

    public Livraison somme(BigDecimal somme) {
        this.somme = somme;
        return this;
    }

    public void setSomme(BigDecimal somme) {
        this.somme = somme;
    }

    public Etat getEtat() {
        return etat;
    }

    public Livraison etat(Etat etat) {
        this.etat = etat;
        return this;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public String getMotif() {
        return motif;
    }

    public Livraison motif(String motif) {
        this.motif = motif;
        return this;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public Livraison transactions(Set<Transaction> transactions) {
        this.transactions = transactions;
        return this;
    }

    public Livraison addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        transaction.setLivraison(this);
        return this;
    }

    public Livraison removeTransaction(Transaction transaction) {
        this.transactions.remove(transaction);
        transaction.setLivraison(null);
        return this;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Set<Frais> getFrais() {
        return frais;
    }

    public Livraison frais(Set<Frais> frais) {
        this.frais = frais;
        return this;
    }

    public Livraison addFrais(Frais frais) {
        this.frais.add(frais);
        frais.setLivraison(this);
        return this;
    }

    public Livraison removeFrais(Frais frais) {
        this.frais.remove(frais);
        frais.setLivraison(null);
        return this;
    }

    public void setFrais(Set<Frais> frais) {
        this.frais = frais;
    }

    public Commande getCommande() {
        return commande;
    }

    public Livraison commande(Commande commande) {
        this.commande = commande;
        return this;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public Livraison fournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
        return this;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public Transport getTransport() {
        return transport;
    }

    public Livraison transport(Transport transport) {
        this.transport = transport;
        return this;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Livraison)) {
            return false;
        }
        return id != null && id.equals(((Livraison) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Livraison{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", date='" + getDate() + "'" +
            ", somme=" + getSomme() +
            ", etat='" + getEtat() + "'" +
            ", motif='" + getMotif() + "'" +
            "}";
    }
}

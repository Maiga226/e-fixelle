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
 * A Commande.
 */
@Entity
@Table(name = "commande")
public class Commande implements Serializable {

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

    @Column(name = "date_livraison_prevu")
    private Instant dateLivraisonPrevu;

    @Enumerated(EnumType.STRING)
    @Column(name = "etat")
    private Etat etat;

    @Column(name = "motif")
    private String motif;

    @Column(name = "avance", precision = 21, scale = 2)
    private BigDecimal avance;

    @Column(name = "avance_en_percent")
    private Boolean avanceEnPercent;

    @OneToMany(mappedBy = "commande")
    private Set<Transaction> transactions = new HashSet<>();

    @OneToMany(mappedBy = "commande")
    private Set<Livraison> livraisons = new HashSet<>();

    @OneToMany(mappedBy = "commande")
    private Set<Paiement> paiements = new HashSet<>();

    @OneToMany(mappedBy = "commande")
    private Set<Frais> frais = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "commandes", allowSetters = true)
    private Fournisseur fournisseur;

    @ManyToOne
    @JsonIgnoreProperties(value = "commandes", allowSetters = true)
    private Client client;

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

    public Commande code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public Commande libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Instant getDate() {
        return date;
    }

    public Commande date(Instant date) {
        this.date = date;
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public BigDecimal getSomme() {
        return somme;
    }

    public Commande somme(BigDecimal somme) {
        this.somme = somme;
        return this;
    }

    public void setSomme(BigDecimal somme) {
        this.somme = somme;
    }

    public Instant getDateLivraisonPrevu() {
        return dateLivraisonPrevu;
    }

    public Commande dateLivraisonPrevu(Instant dateLivraisonPrevu) {
        this.dateLivraisonPrevu = dateLivraisonPrevu;
        return this;
    }

    public void setDateLivraisonPrevu(Instant dateLivraisonPrevu) {
        this.dateLivraisonPrevu = dateLivraisonPrevu;
    }

    public Etat getEtat() {
        return etat;
    }

    public Commande etat(Etat etat) {
        this.etat = etat;
        return this;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public String getMotif() {
        return motif;
    }

    public Commande motif(String motif) {
        this.motif = motif;
        return this;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public BigDecimal getAvance() {
        return avance;
    }

    public Commande avance(BigDecimal avance) {
        this.avance = avance;
        return this;
    }

    public void setAvance(BigDecimal avance) {
        this.avance = avance;
    }

    public Boolean isAvanceEnPercent() {
        return avanceEnPercent;
    }

    public Commande avanceEnPercent(Boolean avanceEnPercent) {
        this.avanceEnPercent = avanceEnPercent;
        return this;
    }

    public void setAvanceEnPercent(Boolean avanceEnPercent) {
        this.avanceEnPercent = avanceEnPercent;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public Commande transactions(Set<Transaction> transactions) {
        this.transactions = transactions;
        return this;
    }

    public Commande addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        transaction.setCommande(this);
        return this;
    }

    public Commande removeTransaction(Transaction transaction) {
        this.transactions.remove(transaction);
        transaction.setCommande(null);
        return this;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Set<Livraison> getLivraisons() {
        return livraisons;
    }

    public Commande livraisons(Set<Livraison> livraisons) {
        this.livraisons = livraisons;
        return this;
    }

    public Commande addLivraison(Livraison livraison) {
        this.livraisons.add(livraison);
        livraison.setCommande(this);
        return this;
    }

    public Commande removeLivraison(Livraison livraison) {
        this.livraisons.remove(livraison);
        livraison.setCommande(null);
        return this;
    }

    public void setLivraisons(Set<Livraison> livraisons) {
        this.livraisons = livraisons;
    }

    public Set<Paiement> getPaiements() {
        return paiements;
    }

    public Commande paiements(Set<Paiement> paiements) {
        this.paiements = paiements;
        return this;
    }

    public Commande addPaiement(Paiement paiement) {
        this.paiements.add(paiement);
        paiement.setCommande(this);
        return this;
    }

    public Commande removePaiement(Paiement paiement) {
        this.paiements.remove(paiement);
        paiement.setCommande(null);
        return this;
    }

    public void setPaiements(Set<Paiement> paiements) {
        this.paiements = paiements;
    }

    public Set<Frais> getFrais() {
        return frais;
    }

    public Commande frais(Set<Frais> frais) {
        this.frais = frais;
        return this;
    }

    public Commande addFrais(Frais frais) {
        this.frais.add(frais);
        frais.setCommande(this);
        return this;
    }

    public Commande removeFrais(Frais frais) {
        this.frais.remove(frais);
        frais.setCommande(null);
        return this;
    }

    public void setFrais(Set<Frais> frais) {
        this.frais = frais;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public Commande fournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
        return this;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public Client getClient() {
        return client;
    }

    public Commande client(Client client) {
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
        if (!(o instanceof Commande)) {
            return false;
        }
        return id != null && id.equals(((Commande) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Commande{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", date='" + getDate() + "'" +
            ", somme=" + getSomme() +
            ", dateLivraisonPrevu='" + getDateLivraisonPrevu() + "'" +
            ", etat='" + getEtat() + "'" +
            ", motif='" + getMotif() + "'" +
            ", avance=" + getAvance() +
            ", avanceEnPercent='" + isAvanceEnPercent() + "'" +
            "}";
    }
}

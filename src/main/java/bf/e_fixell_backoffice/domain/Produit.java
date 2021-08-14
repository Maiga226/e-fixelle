package bf.e_fixell_backoffice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Produit.
 */
@Entity
@Table(name = "produit")
public class Produit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "quantite")
    private Integer quantite;

    @Column(name = "hs_code")
    private String hsCode;

    @OneToMany(mappedBy = "produit")
    private Set<PrixProduit> prixProduits = new HashSet<>();

    @OneToMany(mappedBy = "produit")
    private Set<Perte> pertes = new HashSet<>();

    @OneToMany(mappedBy = "produit")
    private Set<Transaction> transactions = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "produits", allowSetters = true)
    private Categorie categorie;

    @ManyToOne
    @JsonIgnoreProperties(value = "produits", allowSetters = true)
    private Classification classification;

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

    public Produit code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public Produit libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public Produit quantite(Integer quantite) {
        this.quantite = quantite;
        return this;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public String getHsCode() {
        return hsCode;
    }

    public Produit hsCode(String hsCode) {
        this.hsCode = hsCode;
        return this;
    }

    public void setHsCode(String hsCode) {
        this.hsCode = hsCode;
    }

    public Set<PrixProduit> getPrixProduits() {
        return prixProduits;
    }

    public Produit prixProduits(Set<PrixProduit> prixProduits) {
        this.prixProduits = prixProduits;
        return this;
    }

    public Produit addPrixProduit(PrixProduit prixProduit) {
        this.prixProduits.add(prixProduit);
        prixProduit.setProduit(this);
        return this;
    }

    public Produit removePrixProduit(PrixProduit prixProduit) {
        this.prixProduits.remove(prixProduit);
        prixProduit.setProduit(null);
        return this;
    }

    public void setPrixProduits(Set<PrixProduit> prixProduits) {
        this.prixProduits = prixProduits;
    }

    public Set<Perte> getPertes() {
        return pertes;
    }

    public Produit pertes(Set<Perte> pertes) {
        this.pertes = pertes;
        return this;
    }

    public Produit addPerte(Perte perte) {
        this.pertes.add(perte);
        perte.setProduit(this);
        return this;
    }

    public Produit removePerte(Perte perte) {
        this.pertes.remove(perte);
        perte.setProduit(null);
        return this;
    }

    public void setPertes(Set<Perte> pertes) {
        this.pertes = pertes;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public Produit transactions(Set<Transaction> transactions) {
        this.transactions = transactions;
        return this;
    }

    public Produit addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        transaction.setProduit(this);
        return this;
    }

    public Produit removeTransaction(Transaction transaction) {
        this.transactions.remove(transaction);
        transaction.setProduit(null);
        return this;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public Produit categorie(Categorie categorie) {
        this.categorie = categorie;
        return this;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Classification getClassification() {
        return classification;
    }

    public Produit classification(Classification classification) {
        this.classification = classification;
        return this;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Produit)) {
            return false;
        }
        return id != null && id.equals(((Produit) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Produit{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", quantite=" + getQuantite() +
            ", hsCode='" + getHsCode() + "'" +
            "}";
    }
}

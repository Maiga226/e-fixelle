package bf.e_fixell_backoffice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

import bf.e_fixell_backoffice.domain.enumeration.TypeTransaction;

import bf.e_fixell_backoffice.domain.enumeration.Etat;

/**
 * A Transaction.
 */
@Entity
@Table(name = "transaction")
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "date")
    private Instant date;

    @Column(name = "quantite")
    private Integer quantite;

    @Column(name = "prix_unitaire", precision = 21, scale = 2)
    private BigDecimal prixUnitaire;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_transaction")
    private TypeTransaction typeTransaction;

    @Enumerated(EnumType.STRING)
    @Column(name = "etat")
    private Etat etat;

    @Column(name = "motif")
    private String motif;

    @OneToOne
    @JoinColumn(unique = true)
    private FicheTechnique ficheTechnique;

    @ManyToOne
    @JsonIgnoreProperties(value = "transactions", allowSetters = true)
    private Produit produit;

    @ManyToOne
    @JsonIgnoreProperties(value = "transactions", allowSetters = true)
    private Commande commande;

    @ManyToOne
    @JsonIgnoreProperties(value = "transactions", allowSetters = true)
    private Approvisionnement approvisionnement;

    @ManyToOne
    @JsonIgnoreProperties(value = "transactions", allowSetters = true)
    private Livraison livraison;

    @ManyToOne
    @JsonIgnoreProperties(value = "transactions", allowSetters = true)
    private Vente vente;

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

    public Transaction code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Instant getDate() {
        return date;
    }

    public Transaction date(Instant date) {
        this.date = date;
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public Transaction quantite(Integer quantite) {
        this.quantite = quantite;
        return this;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public BigDecimal getPrixUnitaire() {
        return prixUnitaire;
    }

    public Transaction prixUnitaire(BigDecimal prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
        return this;
    }

    public void setPrixUnitaire(BigDecimal prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public TypeTransaction getTypeTransaction() {
        return typeTransaction;
    }

    public Transaction typeTransaction(TypeTransaction typeTransaction) {
        this.typeTransaction = typeTransaction;
        return this;
    }

    public void setTypeTransaction(TypeTransaction typeTransaction) {
        this.typeTransaction = typeTransaction;
    }

    public Etat getEtat() {
        return etat;
    }

    public Transaction etat(Etat etat) {
        this.etat = etat;
        return this;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public String getMotif() {
        return motif;
    }

    public Transaction motif(String motif) {
        this.motif = motif;
        return this;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public FicheTechnique getFicheTechnique() {
        return ficheTechnique;
    }

    public Transaction ficheTechnique(FicheTechnique ficheTechnique) {
        this.ficheTechnique = ficheTechnique;
        return this;
    }

    public void setFicheTechnique(FicheTechnique ficheTechnique) {
        this.ficheTechnique = ficheTechnique;
    }

    public Produit getProduit() {
        return produit;
    }

    public Transaction produit(Produit produit) {
        this.produit = produit;
        return this;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Commande getCommande() {
        return commande;
    }

    public Transaction commande(Commande commande) {
        this.commande = commande;
        return this;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Approvisionnement getApprovisionnement() {
        return approvisionnement;
    }

    public Transaction approvisionnement(Approvisionnement approvisionnement) {
        this.approvisionnement = approvisionnement;
        return this;
    }

    public void setApprovisionnement(Approvisionnement approvisionnement) {
        this.approvisionnement = approvisionnement;
    }

    public Livraison getLivraison() {
        return livraison;
    }

    public Transaction livraison(Livraison livraison) {
        this.livraison = livraison;
        return this;
    }

    public void setLivraison(Livraison livraison) {
        this.livraison = livraison;
    }

    public Vente getVente() {
        return vente;
    }

    public Transaction vente(Vente vente) {
        this.vente = vente;
        return this;
    }

    public void setVente(Vente vente) {
        this.vente = vente;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Transaction)) {
            return false;
        }
        return id != null && id.equals(((Transaction) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Transaction{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", date='" + getDate() + "'" +
            ", quantite=" + getQuantite() +
            ", prixUnitaire=" + getPrixUnitaire() +
            ", typeTransaction='" + getTypeTransaction() + "'" +
            ", etat='" + getEtat() + "'" +
            ", motif='" + getMotif() + "'" +
            "}";
    }
}

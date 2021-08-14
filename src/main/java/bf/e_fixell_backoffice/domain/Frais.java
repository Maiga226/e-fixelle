package bf.e_fixell_backoffice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A Frais.
 */
@Entity
@Table(name = "frais")
public class Frais implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "valeur", precision = 21, scale = 2)
    private BigDecimal valeur;

    @ManyToOne
    @JsonIgnoreProperties(value = "frais", allowSetters = true)
    private Commande commande;

    @ManyToOne
    @JsonIgnoreProperties(value = "frais", allowSetters = true)
    private TypeFrais typeFrais;

    @ManyToOne
    @JsonIgnoreProperties(value = "frais", allowSetters = true)
    private Livraison livraison;

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

    public Frais libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public BigDecimal getValeur() {
        return valeur;
    }

    public Frais valeur(BigDecimal valeur) {
        this.valeur = valeur;
        return this;
    }

    public void setValeur(BigDecimal valeur) {
        this.valeur = valeur;
    }

    public Commande getCommande() {
        return commande;
    }

    public Frais commande(Commande commande) {
        this.commande = commande;
        return this;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public TypeFrais getTypeFrais() {
        return typeFrais;
    }

    public Frais typeFrais(TypeFrais typeFrais) {
        this.typeFrais = typeFrais;
        return this;
    }

    public void setTypeFrais(TypeFrais typeFrais) {
        this.typeFrais = typeFrais;
    }

    public Livraison getLivraison() {
        return livraison;
    }

    public Frais livraison(Livraison livraison) {
        this.livraison = livraison;
        return this;
    }

    public void setLivraison(Livraison livraison) {
        this.livraison = livraison;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Frais)) {
            return false;
        }
        return id != null && id.equals(((Frais) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Frais{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", valeur=" + getValeur() +
            "}";
    }
}

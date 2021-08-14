package bf.e_fixell_backoffice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * A Paiement.
 */
@Entity
@Table(name = "paiement")
public class Paiement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "date")
    private Instant date;

    @Column(name = "montant", precision = 21, scale = 2)
    private BigDecimal montant;

    @ManyToOne
    @JsonIgnoreProperties(value = "paiements", allowSetters = true)
    private Commande commande;

    @ManyToOne
    @JsonIgnoreProperties(value = "paiements", allowSetters = true)
    private Vente vente;

    @ManyToOne
    @JsonIgnoreProperties(value = "paiements", allowSetters = true)
    private SessionCaisse sessioncaisse;

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

    public Paiement code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Instant getDate() {
        return date;
    }

    public Paiement date(Instant date) {
        this.date = date;
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public Paiement montant(BigDecimal montant) {
        this.montant = montant;
        return this;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public Commande getCommande() {
        return commande;
    }

    public Paiement commande(Commande commande) {
        this.commande = commande;
        return this;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Vente getVente() {
        return vente;
    }

    public Paiement vente(Vente vente) {
        this.vente = vente;
        return this;
    }

    public void setVente(Vente vente) {
        this.vente = vente;
    }

    public SessionCaisse getSessioncaisse() {
        return sessioncaisse;
    }

    public Paiement sessioncaisse(SessionCaisse sessionCaisse) {
        this.sessioncaisse = sessionCaisse;
        return this;
    }

    public void setSessioncaisse(SessionCaisse sessionCaisse) {
        this.sessioncaisse = sessionCaisse;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Paiement)) {
            return false;
        }
        return id != null && id.equals(((Paiement) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Paiement{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", date='" + getDate() + "'" +
            ", montant=" + getMontant() +
            "}";
    }
}

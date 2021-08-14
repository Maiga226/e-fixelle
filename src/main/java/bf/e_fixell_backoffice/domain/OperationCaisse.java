package bf.e_fixell_backoffice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

import bf.e_fixell_backoffice.domain.enumeration.TypeOperationCaisse;

/**
 * A OperationCaisse.
 */
@Entity
@Table(name = "operation_caisse")
public class OperationCaisse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_operation_caisse")
    private TypeOperationCaisse typeOperationCaisse;

    @Column(name = "montant", precision = 21, scale = 2)
    private BigDecimal montant;

    @ManyToOne
    @JsonIgnoreProperties(value = "operationCaisses", allowSetters = true)
    private Caisse caisseSrc;

    @ManyToOne
    @JsonIgnoreProperties(value = "operationCaisses", allowSetters = true)
    private Caisse caisseDst;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeOperationCaisse getTypeOperationCaisse() {
        return typeOperationCaisse;
    }

    public OperationCaisse typeOperationCaisse(TypeOperationCaisse typeOperationCaisse) {
        this.typeOperationCaisse = typeOperationCaisse;
        return this;
    }

    public void setTypeOperationCaisse(TypeOperationCaisse typeOperationCaisse) {
        this.typeOperationCaisse = typeOperationCaisse;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public OperationCaisse montant(BigDecimal montant) {
        this.montant = montant;
        return this;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public Caisse getCaisseSrc() {
        return caisseSrc;
    }

    public OperationCaisse caisseSrc(Caisse caisse) {
        this.caisseSrc = caisse;
        return this;
    }

    public void setCaisseSrc(Caisse caisse) {
        this.caisseSrc = caisse;
    }

    public Caisse getCaisseDst() {
        return caisseDst;
    }

    public OperationCaisse caisseDst(Caisse caisse) {
        this.caisseDst = caisse;
        return this;
    }

    public void setCaisseDst(Caisse caisse) {
        this.caisseDst = caisse;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OperationCaisse)) {
            return false;
        }
        return id != null && id.equals(((OperationCaisse) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OperationCaisse{" +
            "id=" + getId() +
            ", typeOperationCaisse='" + getTypeOperationCaisse() + "'" +
            ", montant=" + getMontant() +
            "}";
    }
}

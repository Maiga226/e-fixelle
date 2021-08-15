package bf.e_fixell_backoffice.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import bf.e_fixell_backoffice.domain.enumeration.TypeOperationCaisse;

/**
 * A DTO for the {@link bf.e_fixell_backoffice.domain.OperationCaisse} entity.
 */
public class OperationCaisseDTO implements Serializable {
    
    private Long id;

    private TypeOperationCaisse typeOperationCaisse;

    private BigDecimal montant;


    private Long caisseSrcId;

    private Long caisseDstId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeOperationCaisse getTypeOperationCaisse() {
        return typeOperationCaisse;
    }

    public void setTypeOperationCaisse(TypeOperationCaisse typeOperationCaisse) {
        this.typeOperationCaisse = typeOperationCaisse;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public Long getCaisseSrcId() {
        return caisseSrcId;
    }

    public void setCaisseSrcId(Long caisseId) {
        this.caisseSrcId = caisseId;
    }

    public Long getCaisseDstId() {
        return caisseDstId;
    }

    public void setCaisseDstId(Long caisseId) {
        this.caisseDstId = caisseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OperationCaisseDTO)) {
            return false;
        }

        return id != null && id.equals(((OperationCaisseDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OperationCaisseDTO{" +
            "id=" + getId() +
            ", typeOperationCaisse='" + getTypeOperationCaisse() + "'" +
            ", montant=" + getMontant() +
            ", caisseSrcId=" + getCaisseSrcId() +
            ", caisseDstId=" + getCaisseDstId() +
            "}";
    }
}

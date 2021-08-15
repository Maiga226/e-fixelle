package bf.e_fixell_backoffice.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import bf.e_fixell_backoffice.domain.enumeration.TypeOperationCaisse;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.BigDecimalFilter;

/**
 * Criteria class for the {@link bf.e_fixell_backoffice.domain.OperationCaisse} entity. This class is used
 * in {@link bf.e_fixell_backoffice.web.rest.OperationCaisseResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /operation-caisses?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class OperationCaisseCriteria implements Serializable, Criteria {
    /**
     * Class for filtering TypeOperationCaisse
     */
    public static class TypeOperationCaisseFilter extends Filter<TypeOperationCaisse> {

        public TypeOperationCaisseFilter() {
        }

        public TypeOperationCaisseFilter(TypeOperationCaisseFilter filter) {
            super(filter);
        }

        @Override
        public TypeOperationCaisseFilter copy() {
            return new TypeOperationCaisseFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private TypeOperationCaisseFilter typeOperationCaisse;

    private BigDecimalFilter montant;

    private LongFilter caisseSrcId;

    private LongFilter caisseDstId;

    public OperationCaisseCriteria() {
    }

    public OperationCaisseCriteria(OperationCaisseCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.typeOperationCaisse = other.typeOperationCaisse == null ? null : other.typeOperationCaisse.copy();
        this.montant = other.montant == null ? null : other.montant.copy();
        this.caisseSrcId = other.caisseSrcId == null ? null : other.caisseSrcId.copy();
        this.caisseDstId = other.caisseDstId == null ? null : other.caisseDstId.copy();
    }

    @Override
    public OperationCaisseCriteria copy() {
        return new OperationCaisseCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public TypeOperationCaisseFilter getTypeOperationCaisse() {
        return typeOperationCaisse;
    }

    public void setTypeOperationCaisse(TypeOperationCaisseFilter typeOperationCaisse) {
        this.typeOperationCaisse = typeOperationCaisse;
    }

    public BigDecimalFilter getMontant() {
        return montant;
    }

    public void setMontant(BigDecimalFilter montant) {
        this.montant = montant;
    }

    public LongFilter getCaisseSrcId() {
        return caisseSrcId;
    }

    public void setCaisseSrcId(LongFilter caisseSrcId) {
        this.caisseSrcId = caisseSrcId;
    }

    public LongFilter getCaisseDstId() {
        return caisseDstId;
    }

    public void setCaisseDstId(LongFilter caisseDstId) {
        this.caisseDstId = caisseDstId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final OperationCaisseCriteria that = (OperationCaisseCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(typeOperationCaisse, that.typeOperationCaisse) &&
            Objects.equals(montant, that.montant) &&
            Objects.equals(caisseSrcId, that.caisseSrcId) &&
            Objects.equals(caisseDstId, that.caisseDstId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        typeOperationCaisse,
        montant,
        caisseSrcId,
        caisseDstId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OperationCaisseCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (typeOperationCaisse != null ? "typeOperationCaisse=" + typeOperationCaisse + ", " : "") +
                (montant != null ? "montant=" + montant + ", " : "") +
                (caisseSrcId != null ? "caisseSrcId=" + caisseSrcId + ", " : "") +
                (caisseDstId != null ? "caisseDstId=" + caisseDstId + ", " : "") +
            "}";
    }

}

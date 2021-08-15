package bf.e_fixell_backoffice.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.BigDecimalFilter;
import io.github.jhipster.service.filter.InstantFilter;

/**
 * Criteria class for the {@link bf.e_fixell_backoffice.domain.Approvisionnement} entity. This class is used
 * in {@link bf.e_fixell_backoffice.web.rest.ApprovisionnementResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /approvisionnements?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ApprovisionnementCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter code;

    private StringFilter libelle;

    private InstantFilter date;

    private BigDecimalFilter montant;

    private LongFilter transactionId;

    private LongFilter fournisseurId;

    public ApprovisionnementCriteria() {
    }

    public ApprovisionnementCriteria(ApprovisionnementCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.code = other.code == null ? null : other.code.copy();
        this.libelle = other.libelle == null ? null : other.libelle.copy();
        this.date = other.date == null ? null : other.date.copy();
        this.montant = other.montant == null ? null : other.montant.copy();
        this.transactionId = other.transactionId == null ? null : other.transactionId.copy();
        this.fournisseurId = other.fournisseurId == null ? null : other.fournisseurId.copy();
    }

    @Override
    public ApprovisionnementCriteria copy() {
        return new ApprovisionnementCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getCode() {
        return code;
    }

    public void setCode(StringFilter code) {
        this.code = code;
    }

    public StringFilter getLibelle() {
        return libelle;
    }

    public void setLibelle(StringFilter libelle) {
        this.libelle = libelle;
    }

    public InstantFilter getDate() {
        return date;
    }

    public void setDate(InstantFilter date) {
        this.date = date;
    }

    public BigDecimalFilter getMontant() {
        return montant;
    }

    public void setMontant(BigDecimalFilter montant) {
        this.montant = montant;
    }

    public LongFilter getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(LongFilter transactionId) {
        this.transactionId = transactionId;
    }

    public LongFilter getFournisseurId() {
        return fournisseurId;
    }

    public void setFournisseurId(LongFilter fournisseurId) {
        this.fournisseurId = fournisseurId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ApprovisionnementCriteria that = (ApprovisionnementCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(code, that.code) &&
            Objects.equals(libelle, that.libelle) &&
            Objects.equals(date, that.date) &&
            Objects.equals(montant, that.montant) &&
            Objects.equals(transactionId, that.transactionId) &&
            Objects.equals(fournisseurId, that.fournisseurId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        code,
        libelle,
        date,
        montant,
        transactionId,
        fournisseurId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ApprovisionnementCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (libelle != null ? "libelle=" + libelle + ", " : "") +
                (date != null ? "date=" + date + ", " : "") +
                (montant != null ? "montant=" + montant + ", " : "") +
                (transactionId != null ? "transactionId=" + transactionId + ", " : "") +
                (fournisseurId != null ? "fournisseurId=" + fournisseurId + ", " : "") +
            "}";
    }

}

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

/**
 * Criteria class for the {@link bf.e_fixell_backoffice.domain.Depense} entity. This class is used
 * in {@link bf.e_fixell_backoffice.web.rest.DepenseResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /depenses?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DepenseCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter libelle;

    private StringFilter description;

    private BigDecimalFilter montant;

    private LongFilter typeDepenseId;

    private LongFilter sessionCaisseId;

    public DepenseCriteria() {
    }

    public DepenseCriteria(DepenseCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.libelle = other.libelle == null ? null : other.libelle.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.montant = other.montant == null ? null : other.montant.copy();
        this.typeDepenseId = other.typeDepenseId == null ? null : other.typeDepenseId.copy();
        this.sessionCaisseId = other.sessionCaisseId == null ? null : other.sessionCaisseId.copy();
    }

    @Override
    public DepenseCriteria copy() {
        return new DepenseCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getLibelle() {
        return libelle;
    }

    public void setLibelle(StringFilter libelle) {
        this.libelle = libelle;
    }

    public StringFilter getDescription() {
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public BigDecimalFilter getMontant() {
        return montant;
    }

    public void setMontant(BigDecimalFilter montant) {
        this.montant = montant;
    }

    public LongFilter getTypeDepenseId() {
        return typeDepenseId;
    }

    public void setTypeDepenseId(LongFilter typeDepenseId) {
        this.typeDepenseId = typeDepenseId;
    }

    public LongFilter getSessionCaisseId() {
        return sessionCaisseId;
    }

    public void setSessionCaisseId(LongFilter sessionCaisseId) {
        this.sessionCaisseId = sessionCaisseId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DepenseCriteria that = (DepenseCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(libelle, that.libelle) &&
            Objects.equals(description, that.description) &&
            Objects.equals(montant, that.montant) &&
            Objects.equals(typeDepenseId, that.typeDepenseId) &&
            Objects.equals(sessionCaisseId, that.sessionCaisseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        libelle,
        description,
        montant,
        typeDepenseId,
        sessionCaisseId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DepenseCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (libelle != null ? "libelle=" + libelle + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (montant != null ? "montant=" + montant + ", " : "") +
                (typeDepenseId != null ? "typeDepenseId=" + typeDepenseId + ", " : "") +
                (sessionCaisseId != null ? "sessionCaisseId=" + sessionCaisseId + ", " : "") +
            "}";
    }

}

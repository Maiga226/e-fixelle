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

/**
 * Criteria class for the {@link bf.e_fixell_backoffice.domain.Fonction} entity. This class is used
 * in {@link bf.e_fixell_backoffice.web.rest.FonctionResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /fonctions?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class FonctionCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter libelle;

    private StringFilter description;

    private FloatFilter salaireMin;

    private FloatFilter salaireMax;

    private LongFilter historiqueAffectationId;

    public FonctionCriteria() {
    }

    public FonctionCriteria(FonctionCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.libelle = other.libelle == null ? null : other.libelle.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.salaireMin = other.salaireMin == null ? null : other.salaireMin.copy();
        this.salaireMax = other.salaireMax == null ? null : other.salaireMax.copy();
        this.historiqueAffectationId = other.historiqueAffectationId == null ? null : other.historiqueAffectationId.copy();
    }

    @Override
    public FonctionCriteria copy() {
        return new FonctionCriteria(this);
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

    public FloatFilter getSalaireMin() {
        return salaireMin;
    }

    public void setSalaireMin(FloatFilter salaireMin) {
        this.salaireMin = salaireMin;
    }

    public FloatFilter getSalaireMax() {
        return salaireMax;
    }

    public void setSalaireMax(FloatFilter salaireMax) {
        this.salaireMax = salaireMax;
    }

    public LongFilter getHistoriqueAffectationId() {
        return historiqueAffectationId;
    }

    public void setHistoriqueAffectationId(LongFilter historiqueAffectationId) {
        this.historiqueAffectationId = historiqueAffectationId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final FonctionCriteria that = (FonctionCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(libelle, that.libelle) &&
            Objects.equals(description, that.description) &&
            Objects.equals(salaireMin, that.salaireMin) &&
            Objects.equals(salaireMax, that.salaireMax) &&
            Objects.equals(historiqueAffectationId, that.historiqueAffectationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        libelle,
        description,
        salaireMin,
        salaireMax,
        historiqueAffectationId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FonctionCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (libelle != null ? "libelle=" + libelle + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (salaireMin != null ? "salaireMin=" + salaireMin + ", " : "") +
                (salaireMax != null ? "salaireMax=" + salaireMax + ", " : "") +
                (historiqueAffectationId != null ? "historiqueAffectationId=" + historiqueAffectationId + ", " : "") +
            "}";
    }

}

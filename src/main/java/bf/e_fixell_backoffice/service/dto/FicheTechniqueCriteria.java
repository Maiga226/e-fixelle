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
 * Criteria class for the {@link bf.e_fixell_backoffice.domain.FicheTechnique} entity. This class is used
 * in {@link bf.e_fixell_backoffice.web.rest.FicheTechniqueResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /fiche-techniques?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class FicheTechniqueCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter libelle;

    private LongFilter caracteristiqueId;

    public FicheTechniqueCriteria() {
    }

    public FicheTechniqueCriteria(FicheTechniqueCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.libelle = other.libelle == null ? null : other.libelle.copy();
        this.caracteristiqueId = other.caracteristiqueId == null ? null : other.caracteristiqueId.copy();
    }

    @Override
    public FicheTechniqueCriteria copy() {
        return new FicheTechniqueCriteria(this);
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

    public LongFilter getCaracteristiqueId() {
        return caracteristiqueId;
    }

    public void setCaracteristiqueId(LongFilter caracteristiqueId) {
        this.caracteristiqueId = caracteristiqueId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final FicheTechniqueCriteria that = (FicheTechniqueCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(libelle, that.libelle) &&
            Objects.equals(caracteristiqueId, that.caracteristiqueId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        libelle,
        caracteristiqueId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FicheTechniqueCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (libelle != null ? "libelle=" + libelle + ", " : "") +
                (caracteristiqueId != null ? "caracteristiqueId=" + caracteristiqueId + ", " : "") +
            "}";
    }

}

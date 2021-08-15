package bf.e_fixell_backoffice.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import bf.e_fixell_backoffice.domain.enumeration.TypeUniteOrganisation;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link bf.e_fixell_backoffice.domain.UniteOrganisation} entity. This class is used
 * in {@link bf.e_fixell_backoffice.web.rest.UniteOrganisationResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /unite-organisations?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class UniteOrganisationCriteria implements Serializable, Criteria {
    /**
     * Class for filtering TypeUniteOrganisation
     */
    public static class TypeUniteOrganisationFilter extends Filter<TypeUniteOrganisation> {

        public TypeUniteOrganisationFilter() {
        }

        public TypeUniteOrganisationFilter(TypeUniteOrganisationFilter filter) {
            super(filter);
        }

        @Override
        public TypeUniteOrganisationFilter copy() {
            return new TypeUniteOrganisationFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter libelle;

    private TypeUniteOrganisationFilter type;

    private LongFilter fournisseurId;

    public UniteOrganisationCriteria() {
    }

    public UniteOrganisationCriteria(UniteOrganisationCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.libelle = other.libelle == null ? null : other.libelle.copy();
        this.type = other.type == null ? null : other.type.copy();
        this.fournisseurId = other.fournisseurId == null ? null : other.fournisseurId.copy();
    }

    @Override
    public UniteOrganisationCriteria copy() {
        return new UniteOrganisationCriteria(this);
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

    public TypeUniteOrganisationFilter getType() {
        return type;
    }

    public void setType(TypeUniteOrganisationFilter type) {
        this.type = type;
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
        final UniteOrganisationCriteria that = (UniteOrganisationCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(libelle, that.libelle) &&
            Objects.equals(type, that.type) &&
            Objects.equals(fournisseurId, that.fournisseurId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        libelle,
        type,
        fournisseurId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UniteOrganisationCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (libelle != null ? "libelle=" + libelle + ", " : "") +
                (type != null ? "type=" + type + ", " : "") +
                (fournisseurId != null ? "fournisseurId=" + fournisseurId + ", " : "") +
            "}";
    }

}

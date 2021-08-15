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
 * Criteria class for the {@link bf.e_fixell_backoffice.domain.HistoriqueAffectation} entity. This class is used
 * in {@link bf.e_fixell_backoffice.web.rest.HistoriqueAffectationResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /historique-affectations?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class HistoriqueAffectationCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private InstantFilter dateDebut;

    private InstantFilter dateFin;

    private BigDecimalFilter salaire;

    private LongFilter personnelId;

    private LongFilter fonctionId;

    public HistoriqueAffectationCriteria() {
    }

    public HistoriqueAffectationCriteria(HistoriqueAffectationCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.dateDebut = other.dateDebut == null ? null : other.dateDebut.copy();
        this.dateFin = other.dateFin == null ? null : other.dateFin.copy();
        this.salaire = other.salaire == null ? null : other.salaire.copy();
        this.personnelId = other.personnelId == null ? null : other.personnelId.copy();
        this.fonctionId = other.fonctionId == null ? null : other.fonctionId.copy();
    }

    @Override
    public HistoriqueAffectationCriteria copy() {
        return new HistoriqueAffectationCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public InstantFilter getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(InstantFilter dateDebut) {
        this.dateDebut = dateDebut;
    }

    public InstantFilter getDateFin() {
        return dateFin;
    }

    public void setDateFin(InstantFilter dateFin) {
        this.dateFin = dateFin;
    }

    public BigDecimalFilter getSalaire() {
        return salaire;
    }

    public void setSalaire(BigDecimalFilter salaire) {
        this.salaire = salaire;
    }

    public LongFilter getPersonnelId() {
        return personnelId;
    }

    public void setPersonnelId(LongFilter personnelId) {
        this.personnelId = personnelId;
    }

    public LongFilter getFonctionId() {
        return fonctionId;
    }

    public void setFonctionId(LongFilter fonctionId) {
        this.fonctionId = fonctionId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final HistoriqueAffectationCriteria that = (HistoriqueAffectationCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(dateDebut, that.dateDebut) &&
            Objects.equals(dateFin, that.dateFin) &&
            Objects.equals(salaire, that.salaire) &&
            Objects.equals(personnelId, that.personnelId) &&
            Objects.equals(fonctionId, that.fonctionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        dateDebut,
        dateFin,
        salaire,
        personnelId,
        fonctionId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HistoriqueAffectationCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (dateDebut != null ? "dateDebut=" + dateDebut + ", " : "") +
                (dateFin != null ? "dateFin=" + dateFin + ", " : "") +
                (salaire != null ? "salaire=" + salaire + ", " : "") +
                (personnelId != null ? "personnelId=" + personnelId + ", " : "") +
                (fonctionId != null ? "fonctionId=" + fonctionId + ", " : "") +
            "}";
    }

}

package bf.e_fixell_backoffice.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import bf.e_fixell_backoffice.domain.enumeration.Statut;
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
 * Criteria class for the {@link bf.e_fixell_backoffice.domain.PrixProduit} entity. This class is used
 * in {@link bf.e_fixell_backoffice.web.rest.PrixProduitResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /prix-produits?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PrixProduitCriteria implements Serializable, Criteria {
    /**
     * Class for filtering Statut
     */
    public static class StatutFilter extends Filter<Statut> {

        public StatutFilter() {
        }

        public StatutFilter(StatutFilter filter) {
            super(filter);
        }

        @Override
        public StatutFilter copy() {
            return new StatutFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private InstantFilter dateDebut;

    private InstantFilter dateFin;

    private BigDecimalFilter prix;

    private StatutFilter statut;

    private LongFilter produitId;

    public PrixProduitCriteria() {
    }

    public PrixProduitCriteria(PrixProduitCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.dateDebut = other.dateDebut == null ? null : other.dateDebut.copy();
        this.dateFin = other.dateFin == null ? null : other.dateFin.copy();
        this.prix = other.prix == null ? null : other.prix.copy();
        this.statut = other.statut == null ? null : other.statut.copy();
        this.produitId = other.produitId == null ? null : other.produitId.copy();
    }

    @Override
    public PrixProduitCriteria copy() {
        return new PrixProduitCriteria(this);
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

    public BigDecimalFilter getPrix() {
        return prix;
    }

    public void setPrix(BigDecimalFilter prix) {
        this.prix = prix;
    }

    public StatutFilter getStatut() {
        return statut;
    }

    public void setStatut(StatutFilter statut) {
        this.statut = statut;
    }

    public LongFilter getProduitId() {
        return produitId;
    }

    public void setProduitId(LongFilter produitId) {
        this.produitId = produitId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PrixProduitCriteria that = (PrixProduitCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(dateDebut, that.dateDebut) &&
            Objects.equals(dateFin, that.dateFin) &&
            Objects.equals(prix, that.prix) &&
            Objects.equals(statut, that.statut) &&
            Objects.equals(produitId, that.produitId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        dateDebut,
        dateFin,
        prix,
        statut,
        produitId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PrixProduitCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (dateDebut != null ? "dateDebut=" + dateDebut + ", " : "") +
                (dateFin != null ? "dateFin=" + dateFin + ", " : "") +
                (prix != null ? "prix=" + prix + ", " : "") +
                (statut != null ? "statut=" + statut + ", " : "") +
                (produitId != null ? "produitId=" + produitId + ", " : "") +
            "}";
    }

}

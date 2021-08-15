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
 * Criteria class for the {@link bf.e_fixell_backoffice.domain.Perte} entity. This class is used
 * in {@link bf.e_fixell_backoffice.web.rest.PerteResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /pertes?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PerteCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter libelle;

    private InstantFilter date;

    private IntegerFilter quantite;

    private BigDecimalFilter montant;

    private LongFilter produitId;

    public PerteCriteria() {
    }

    public PerteCriteria(PerteCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.libelle = other.libelle == null ? null : other.libelle.copy();
        this.date = other.date == null ? null : other.date.copy();
        this.quantite = other.quantite == null ? null : other.quantite.copy();
        this.montant = other.montant == null ? null : other.montant.copy();
        this.produitId = other.produitId == null ? null : other.produitId.copy();
    }

    @Override
    public PerteCriteria copy() {
        return new PerteCriteria(this);
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

    public InstantFilter getDate() {
        return date;
    }

    public void setDate(InstantFilter date) {
        this.date = date;
    }

    public IntegerFilter getQuantite() {
        return quantite;
    }

    public void setQuantite(IntegerFilter quantite) {
        this.quantite = quantite;
    }

    public BigDecimalFilter getMontant() {
        return montant;
    }

    public void setMontant(BigDecimalFilter montant) {
        this.montant = montant;
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
        final PerteCriteria that = (PerteCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(libelle, that.libelle) &&
            Objects.equals(date, that.date) &&
            Objects.equals(quantite, that.quantite) &&
            Objects.equals(montant, that.montant) &&
            Objects.equals(produitId, that.produitId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        libelle,
        date,
        quantite,
        montant,
        produitId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PerteCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (libelle != null ? "libelle=" + libelle + ", " : "") +
                (date != null ? "date=" + date + ", " : "") +
                (quantite != null ? "quantite=" + quantite + ", " : "") +
                (montant != null ? "montant=" + montant + ", " : "") +
                (produitId != null ? "produitId=" + produitId + ", " : "") +
            "}";
    }

}

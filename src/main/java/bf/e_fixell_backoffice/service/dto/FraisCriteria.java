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
 * Criteria class for the {@link bf.e_fixell_backoffice.domain.Frais} entity. This class is used
 * in {@link bf.e_fixell_backoffice.web.rest.FraisResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /frais?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class FraisCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter libelle;

    private BigDecimalFilter valeur;

    private LongFilter commandeId;

    private LongFilter typeFraisId;

    private LongFilter livraisonId;

    public FraisCriteria() {
    }

    public FraisCriteria(FraisCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.libelle = other.libelle == null ? null : other.libelle.copy();
        this.valeur = other.valeur == null ? null : other.valeur.copy();
        this.commandeId = other.commandeId == null ? null : other.commandeId.copy();
        this.typeFraisId = other.typeFraisId == null ? null : other.typeFraisId.copy();
        this.livraisonId = other.livraisonId == null ? null : other.livraisonId.copy();
    }

    @Override
    public FraisCriteria copy() {
        return new FraisCriteria(this);
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

    public BigDecimalFilter getValeur() {
        return valeur;
    }

    public void setValeur(BigDecimalFilter valeur) {
        this.valeur = valeur;
    }

    public LongFilter getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(LongFilter commandeId) {
        this.commandeId = commandeId;
    }

    public LongFilter getTypeFraisId() {
        return typeFraisId;
    }

    public void setTypeFraisId(LongFilter typeFraisId) {
        this.typeFraisId = typeFraisId;
    }

    public LongFilter getLivraisonId() {
        return livraisonId;
    }

    public void setLivraisonId(LongFilter livraisonId) {
        this.livraisonId = livraisonId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final FraisCriteria that = (FraisCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(libelle, that.libelle) &&
            Objects.equals(valeur, that.valeur) &&
            Objects.equals(commandeId, that.commandeId) &&
            Objects.equals(typeFraisId, that.typeFraisId) &&
            Objects.equals(livraisonId, that.livraisonId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        libelle,
        valeur,
        commandeId,
        typeFraisId,
        livraisonId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FraisCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (libelle != null ? "libelle=" + libelle + ", " : "") +
                (valeur != null ? "valeur=" + valeur + ", " : "") +
                (commandeId != null ? "commandeId=" + commandeId + ", " : "") +
                (typeFraisId != null ? "typeFraisId=" + typeFraisId + ", " : "") +
                (livraisonId != null ? "livraisonId=" + livraisonId + ", " : "") +
            "}";
    }

}

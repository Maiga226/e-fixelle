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
 * Criteria class for the {@link bf.e_fixell_backoffice.domain.Paiement} entity. This class is used
 * in {@link bf.e_fixell_backoffice.web.rest.PaiementResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /paiements?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PaiementCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter code;

    private InstantFilter date;

    private BigDecimalFilter montant;

    private LongFilter commandeId;

    private LongFilter venteId;

    private LongFilter sessioncaisseId;

    public PaiementCriteria() {
    }

    public PaiementCriteria(PaiementCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.code = other.code == null ? null : other.code.copy();
        this.date = other.date == null ? null : other.date.copy();
        this.montant = other.montant == null ? null : other.montant.copy();
        this.commandeId = other.commandeId == null ? null : other.commandeId.copy();
        this.venteId = other.venteId == null ? null : other.venteId.copy();
        this.sessioncaisseId = other.sessioncaisseId == null ? null : other.sessioncaisseId.copy();
    }

    @Override
    public PaiementCriteria copy() {
        return new PaiementCriteria(this);
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

    public LongFilter getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(LongFilter commandeId) {
        this.commandeId = commandeId;
    }

    public LongFilter getVenteId() {
        return venteId;
    }

    public void setVenteId(LongFilter venteId) {
        this.venteId = venteId;
    }

    public LongFilter getSessioncaisseId() {
        return sessioncaisseId;
    }

    public void setSessioncaisseId(LongFilter sessioncaisseId) {
        this.sessioncaisseId = sessioncaisseId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PaiementCriteria that = (PaiementCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(code, that.code) &&
            Objects.equals(date, that.date) &&
            Objects.equals(montant, that.montant) &&
            Objects.equals(commandeId, that.commandeId) &&
            Objects.equals(venteId, that.venteId) &&
            Objects.equals(sessioncaisseId, that.sessioncaisseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        code,
        date,
        montant,
        commandeId,
        venteId,
        sessioncaisseId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PaiementCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (date != null ? "date=" + date + ", " : "") +
                (montant != null ? "montant=" + montant + ", " : "") +
                (commandeId != null ? "commandeId=" + commandeId + ", " : "") +
                (venteId != null ? "venteId=" + venteId + ", " : "") +
                (sessioncaisseId != null ? "sessioncaisseId=" + sessioncaisseId + ", " : "") +
            "}";
    }

}

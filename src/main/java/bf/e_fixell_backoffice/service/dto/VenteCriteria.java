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
 * Criteria class for the {@link bf.e_fixell_backoffice.domain.Vente} entity. This class is used
 * in {@link bf.e_fixell_backoffice.web.rest.VenteResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /ventes?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class VenteCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter libelle;

    private StringFilter code;

    private InstantFilter date;

    private BigDecimalFilter montant;

    private BigDecimalFilter reste;

    private BooleanFilter solder;

    private LongFilter transactionId;

    private LongFilter paiementId;

    private LongFilter clientId;

    public VenteCriteria() {
    }

    public VenteCriteria(VenteCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.libelle = other.libelle == null ? null : other.libelle.copy();
        this.code = other.code == null ? null : other.code.copy();
        this.date = other.date == null ? null : other.date.copy();
        this.montant = other.montant == null ? null : other.montant.copy();
        this.reste = other.reste == null ? null : other.reste.copy();
        this.solder = other.solder == null ? null : other.solder.copy();
        this.transactionId = other.transactionId == null ? null : other.transactionId.copy();
        this.paiementId = other.paiementId == null ? null : other.paiementId.copy();
        this.clientId = other.clientId == null ? null : other.clientId.copy();
    }

    @Override
    public VenteCriteria copy() {
        return new VenteCriteria(this);
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

    public BigDecimalFilter getReste() {
        return reste;
    }

    public void setReste(BigDecimalFilter reste) {
        this.reste = reste;
    }

    public BooleanFilter getSolder() {
        return solder;
    }

    public void setSolder(BooleanFilter solder) {
        this.solder = solder;
    }

    public LongFilter getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(LongFilter transactionId) {
        this.transactionId = transactionId;
    }

    public LongFilter getPaiementId() {
        return paiementId;
    }

    public void setPaiementId(LongFilter paiementId) {
        this.paiementId = paiementId;
    }

    public LongFilter getClientId() {
        return clientId;
    }

    public void setClientId(LongFilter clientId) {
        this.clientId = clientId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final VenteCriteria that = (VenteCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(libelle, that.libelle) &&
            Objects.equals(code, that.code) &&
            Objects.equals(date, that.date) &&
            Objects.equals(montant, that.montant) &&
            Objects.equals(reste, that.reste) &&
            Objects.equals(solder, that.solder) &&
            Objects.equals(transactionId, that.transactionId) &&
            Objects.equals(paiementId, that.paiementId) &&
            Objects.equals(clientId, that.clientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        libelle,
        code,
        date,
        montant,
        reste,
        solder,
        transactionId,
        paiementId,
        clientId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VenteCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (libelle != null ? "libelle=" + libelle + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (date != null ? "date=" + date + ", " : "") +
                (montant != null ? "montant=" + montant + ", " : "") +
                (reste != null ? "reste=" + reste + ", " : "") +
                (solder != null ? "solder=" + solder + ", " : "") +
                (transactionId != null ? "transactionId=" + transactionId + ", " : "") +
                (paiementId != null ? "paiementId=" + paiementId + ", " : "") +
                (clientId != null ? "clientId=" + clientId + ", " : "") +
            "}";
    }

}
